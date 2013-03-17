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

			$cat = Category::model()->findAll("id_kategori = '" . addslashes($params['category_id']) . "'");

			if ($cat) {
				// found
				$success = true;

				$cat = $cat[0];
				$categoryID = $cat->id_kategori;
				$categoryName = $cat->nama_kategori;
				$canDeleteCategory = $cat->id_user == $this->app->currentUserId;

				$ret = Task::model()->findAll("id_kategori = '" . (int) 
								$params['category_id'] . "'");
			}
			else {
				// not found
				$success = false;
				$ret = array();
			}
		}
		else {
			// retrieve all
			$success = true;
			$ret = Task::model()->findAll();
		}

		$tasks = array();
		foreach ($ret as $task) {
			$dummy = new StdClass;
			$dummy->name = $task->nama_task;
			$dummy->id = $task->id_task;
			$dummy->done = (bool) $task->status;
			$dl = new DateTime($task->deadline);
			$dummy->deadline = $dl->format('j F Y');

			$tags = $task->getTags();
			$dummy->tags = array();
			foreach ($tags as $tag) {
				$dummy->tags[] = $tag->tag;
			}

			$tasks[] = $dummy;
		}

		return compact('success', 'tasks', 'categoryID', 'categoryName', 'canDeleteCategory');
	}

	public function retrieve_categories() {
		// TODO use categories by user
		$raw = Category::model()->findAll();

		$cats = array();
		foreach ($raw as $cat) {
			$dummy = new StdClass;
			$dummy->name = $cat->nama_kategori;
			$dummy->id = $cat->id_kategori;
			$dummy->canDelete = ($cat->id_user == $this->app->currentUserId);

			$cats[] = $dummy;
		}

		return $cats;
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
			
			if (($params['username'] == "admin") && ($params['password'] == "admin123")) {
				// TODO add database using user model
				$_SESSION['user_id'] = 1;
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
			&& (ISSET($params['name'])) && (ISSET($params['birth_date']))
			//&& (ISSET($_POST['avatar']))
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
				array_merge($return["error"], $temperror);
			}
			
			if (!$user->save())
			{
				// record exist
				$return["status"] = "fail";
				$return["error"]["exist"] = "Username/email already exists";
			}
			return $status;
		}
		else
		{
			$return["status"] = "fail";
		}

		return $return;
	}

	public function add_category() {
		if (!$_POST)
			return;

		$nama_kategori = $_POST['nama_kategori'];
		$id_user = $this->app->currentUserId; // the creator of the category

		$category = Category::model();
		$category->nama_kategori = $nama_kategori;
		$category->id_user = $id_user;
		$category->save();

		$usernames = $_POST['usernames']; // an array of usernames, if using facebook-style
		$usernames_list = $_POST['usernames_list'];
		if (!$usernames && $usernames_list) {
			$usernames = explode(';', $usernames_list);
		}
		foreach ($usernames as $k => $v) {
			$usernames[$k] = trim($v);
		}
		if ($usernames) {
			// Find the IDs of the users
			$escapedUsernames = array();
			foreach ($usernames as $k => $v) {
				$escapedUsernames[] = "'" . addslashes($v) . "'";
			}
			$escapedUsernames = implode(',', $escapedUsernames);
			$escapedUsernames = '(' . $escapedUsernames . ')';

			$q = "username IN $escapedUsernames";
			$users = User::model()->findAll($q);

			// Insert into relationship table
			foreach ($users as $user) {
				$insert = "INSERT INTO edit_kategori (id_user, id_katego) VALUES ({$user->id_user}, {$category->id_kategori})";
				DBConnection::DBQuery($insert);
			}
		}

		return array('categoryID' => $category->id_kategori, 'categoryName' => $category->nama_kategori, 'categories' => $this->retrieve_categories());
	}

	public function delete_category() {
		$id_kategori = addslashes($_POST['category_id']);
		$id_user = addslashes($this->app->currentUserId);

		$delete = DBConnection::DBquery("DELETE FROM kategori WHERE id_kategori=$id_kategori AND id_user=$id_user");
		$affectedRows = DBConnection::affectedRows();

		if ($affectedRows) {
			// delete was success
			$success = true;
		}
		else {
			$success = false;
		}

		return array(
			'success' => $success,
			'categoryID' => $id_kategori
		);
	}

	public function mark_task($params) {
		$start = microtime();
		$id_task = addslashes($_POST['taskID']);
		$completed = $_POST['completed'] == 'true' ? 1 : 0;

		// TODO permissions

		$update = "UPDATE task SET status=$completed WHERE id_task='$id_task'";

		$q = DBConnection::DBquery($update);
		if (DBConnection::affectedRows()) {
			return array('success' => 'true', 'taskId' => $id_task);
		}
		else {
			return array('success' => 'false');
		}
	}
}

?>