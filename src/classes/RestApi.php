<?php

// REST API Wrapper
// How to use:
// * Every public method of this class will be 

class RestApi 
{
	protected $app;

	public function __construct($app) 
	{
		$this->app = $app;
	}
	
	public function fetch_latest_task($params)
	{
		$ret = array();
		if (isset($params['task_id']))
		{
			if (isset($params['category_id']))
			{
				// TODO check session current user
				// retrieve based on category
				$ret = Task::model()->findAll("task_id > ".$params['task_id']." AND EXISTS (SELECT * FROM ".Categoory::tableName().
					" WHERE category_id = " . $params['category_id']." AND task_id = task.id)");
			}
			else
			{
				// retrieve all
				$ret = Task::model()->findAll("task_id > ".$params['task_id']);
			}
		}
		return $ret;
	}
	
	public function retrieve_tags($params)
	{
		$ret = array();
		if (isset($params['tags']))
		{
			// retrieve based on existing tags
			$condition = "";
			foreach($params['tags'] as $tag)
			{
				$condition .= "tag_name != ".$tag." ";
			}
			$ret = Tag::model()->findAll($condition);
		}
		else
		{
			// retrieve all
			$ret = Tag::model()->findAll();
		}
		return $ret;
	}
	
	public function retrieve_users($params)
	{
		$ret = array();
		if (isset($params['users']))
		{
			// retrieve based on existing users
			$condition = "";
			foreach($params['users'] as $user)
			{
				$condition .= "username != ".$user." ";
			}
			$ret = User::model()->findAll($condition, array("username"));
		}
		else
		{
			// retrieve all
			$ret = User::model()->findAll("", array("username"));
		}
		return $ret;
	}
	
	public function retrieve_tasks($params)
	{
		$ret = array();
		if (isset($params['category_id']))
		{
			// TODO check if user session is in some category
			// retrieve based on category
			$ret = Task::model()->findAll("EXISTS (SELECT * FROM ".Category::tableName()." WHERE category_id = " . 
							$params['category_id']." AND task_id = task.id)");
		}
		else
		{
			// retrieve all
			$ret = Task::model()->findAll();
		}

		$tasks = array();
		foreach ($ret as $task) {
			$dummy = new StdClass;
			$dummy->name = $task->nama_task;
			$dummy->id = $task->id_task;
			$dl = new DateTime($task->deadline);
			$dummy->deadline = $dl->format('j F Y');

			$tags = $task->getTags();
			$dummy->tags = array();
			foreach ($tags as $tag) {
				$dummy->tags[] = $tag->tag;
			}

			$tasks[] = $dummy;
		}

		return $tasks;
	}
	
	public function comment($params)
	{
		$return = "fail";
		if (($_SERVER['REQUEST_METHOD'] === 'POST') && (isset($params['comment'])) && (isset($params['commentator'])))
		{
			// TODO cek validasi comentator sesuai dengan session
			$comment = new Comment();
			$comment->data = $params;
			if ($comment->save())
			{
				$return = "success";
			}
		}
		return $return;
	}
	
	public function login($params) 
	{
		$return = array();
		if (($_SERVER['REQUEST_METHOD'] === 'POST') &&
			(isset($params['username'])) && (isset($params['password']))) {
			
			$user = User::model()->find("username='".$params['username']."' AND password='".$params['password']."'");
			if ($user)
			{
				$_SESSION['user_id'] = $user->id_user;
				$u = new User;
				$u->fullname = 'Jean Valjean';
				$u->username = 'admin';
				$u->email = 'jean.valjean@gmail.com';

				$_SESSION['current_user'] = $u;

				$return["status"] = "success";
			}
			else {
				$return["status"] = "fail";
			}
			
		}
		else {
			$return["status"] = "fail";
		}

		return $return;
	}

	public function logout($params) 
	{
		$full_url = $_SESSION['full_url'];
		$this->app->destroySession();
		header("Location:".$full_url."index.php");
	}

	public function register_check($params) 
	{
		$return = array();
		if (($_SERVER['REQUEST_METHOD'] === 'POST') && (ISSET($params['username'])) && (ISSET($params['email']))
			&& (ISSET($params['password'])) && (ISSET($params['confirm_password'])) 
			&& (ISSET($params['fullname'])) && (ISSET($params['birthdate']))
			&& (ISSET($params['avatar']))
			)
		{
			$return["status"] = "success";
			$return["error"] = array();
			
			$user = new User();
			$user->data = $params;
			$temperror = $user->checkValidity();
			
			if ($temperror)
			{
				$return["status"] = "fail";
				$return["error"] = array_merge($return["error"], $temperror);
			}
			
			if (User::model()->find("username='".$params['username']."' OR email='".$params['email']."'")->data)
			{
				$return["status"] = "fail";
				$return["error"]["duplicate"] .= "Username/email sudah digunakan.";
			}
		}
		else
		{
			$return["status"] = "fail";
		}

		return $return;
	}
}

?>