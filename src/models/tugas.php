<?php

class Tugas extends Model
{
    private $_id_tugas;
    private $_id_kategori;
	private $_taskname;
	private $_attachment;
    private $_tgl_deadline;
    private $_status;
    private $_last_mod;
    private $_pemilik;
	private $_tag;
	private $_kategori;
	private $_assignees;
	private $_priviledge;
	
	public function get_id_tugas() { return $this->_id_tugas; } 
	public function get_taskname() { return $this->_taskname; } 
	public function get_attachment() { return $this->_attachment; } 
	public function get_attachment2($i) { return $this->_attachment[$i]; } 
	public function get_tgl_deadline() { return $this->_tgl_deadline; } 
	public function get_status() { return $this->_status; } 
	public function get_last_mod() { return $this->_last_mod; } 
	public function get_pemilik() { return $this->_pemilik; } 
	public function get_asignee() { return $this->_asignee; } 
	public function get_kategori() { return $this->_kategori; } 
	public function get_id_kategori() { return $this->_id_kategori; } 
	public function get_tag() { return $this->_tag; } 
	public function get_tag2($i) { return $this->_tag[$i]; } 
	public function set_id_tugas($x) { $this->_id_tugas = ($x); } 
	public function set_taskname($x) { $this->_taskname = $x; } 
	public function set_attachment($x) { $this->_attachment = $x; } 
	public function set_attachment2($i,$x) { $this->_attachment[$i] = $x; } 
	public function set_tgl_deadline($x) { $this->_tgl_deadline = $x; } 
	public function set_status($x) { $this->_status = $x; } 
	public function set_last_mod($x) { $this->_last_mod = $x; } 
	public function set_pemilik($x) { $this->_pemilik = $x; } 
	public function set_kategori($x) { $this->_kategori = $x; } 
	public function set_id_kategori($x) { $this->_id_kategori = $x; } 
	public function set_tag($x) { $this->_tag = $x; } 
	public function set_tag2($i,$x) { $this->_tag[$i] = $x; } 
	public function set_asignee($x) { $this->_asignee = $x; } 
	public function set_asignee2($i,$x) { $this->_asignee[$i] = $x; } 
	
	public function fromArray($tugas)
	{
		$this->_id_tugas = $tugas["id"];
		$this->_taskname = $tugas["nama"];
		$this->_attachment = $tugas["attachment"];
		$this->_tgl_deadline = $tugas["tgl_deadline"];
		$this->_status = $tugas["status"];
		$this->_last_mod = $tugas["last_mod"];
		$this->_pemilik = $tugas["pemilik"];
		$this->_tag = $tugas["tag"];
		$this->_assignees = $tugas["assignees"];
		$this->_kategori = $tugas["kategori"];
		$this->_id_kategori = $tugas["id_kategori"];
		//$this->_priviledge = $tugas["priviledge"];
	}
	
	public function toArray()
	{
		$tugas = array();
		$tugas["id"] = $this->_id_tugas;
		$tugas["nama"] = $this->_taskname;
		$tugas["attachment"] = $this->_attachment;
		$tugas["tgl_deadline"] = $this->_tgl_deadline;
		$tugas["status"] = $this->_status;
		$tugas["last_mod"] = $this->_last_mod;
		$tugas["pemilik"] = $this->_pemilik;
		$tugas["tag"] = $this->_tag;
		$tugas["assignees"] = $this->_assignees;
		$tugas["kategori"] = $this->_kategori;
		$tugas["id_kategori"] = $this->_id_kategori;
		$tugas["priviledge"] = $this->_priviledge;
		return $tugas;
	}
	
	public function getTugas($id_tugas, $username = null)
	{
		$sql = "SELECT t.id AS id, t.nama AS nama, tgl_deadline,  `status` , t.last_mod AS last_mod, pemilik, id_kategori, c.nama AS nama_kategori
				FROM categories c, tugas t
				WHERE t.id = ? AND c.id = t.id_kategori;";

		$this->_setSql($sql);
		
		$tugas = $this->getRow(array($id_tugas));
		 
		if (empty($tugas))
		{
			return false;
		}
		
		$this->_id_tugas = $tugas["id"];
		$this->_taskname = $tugas["nama"];
		$this->_tgl_deadline = $tugas["tgl_deadline"];
		$this->_status = $tugas["status"];
		$this->_last_mod = $tugas["last_mod"];
		$this->_pemilik = $tugas["pemilik"];
		$this->_id_kategori = $tugas["id_kategori"];
		$this->_kategori = $tugas["nama_kategori"];

		$this->_tag = $this->getTags($id_tugas);
		$this->_attachment = $this->getAttachments($id_tugas);
		$this->_assignees = $this->getAssignees($id_tugas);
		if ($username != null)
		{
			$this->_priviledge = $this->isPriviledge($id_tugas, $username);
		}
		else
		{
			$this->_priviledge = false;
		}

		return $this->toArray();
	}
	
	public function getTags($id_tugas)
	{
		$sql = "SELECT tag FROM tags WHERE id_tugas=? ";
		$this->_setSql($sql);
		
		$r = $this->getAll(array($id_tugas));
		$retval = array();
		
		$i = 0;
		while(!empty($r[$i]["tag"]))
		{
			$retval[$i] = $r[$i]["tag"];
			$i++;
		}

		return $retval;
	}

	public function getAssignees($id_tugas)
	{
		$sql = "SELECT a.username AS username, u.full_name AS full_name, u.avatar AS avatar FROM assignees a, users u WHERE id_tugas=? AND a.username=u.username";
		$this->_setSql($sql);
		return $this->getAll(array($id_tugas));
	}
	
	public function getAttachments($id_tugas)
	{
		$sql = "SELECT id_attachment, name, filename, type FROM attachments WHERE id_tugas=?";
		$this->_setSql($sql);
		return $this->getAll(array($id_tugas));
	}


	public function setStats($i, $n)
    {
        $sql = "UPDATE `tugas` SET `status` = ? WHERE `id` = ?;";
         
        $data = array(
			$n,
			$i
        );
         
        $sth = $this->_db->prepare($sql);
        return $sth->execute($data);
    }

	public function getAsignee2($id_tugas)
	{
		$sql = "SELECT username FROM assignees WHERE id_tugas=? ";
        $this->_setSql($sql);
		
        $r = $this->getAll(array($id_tugas));
		
		
        if (empty($r))
        {
            return false;
        }
		
		return $r;
	}
	
	public function addAssignee($id_tugas, $username)
	{
		$sql="INSERT INTO `assignees`(`id_tugas`, `username`) VALUES (?, ?);";
		$data = array(
			$id_tugas,
			$username
		);
		
		$sth = $this->_db->prepare($sql);
		$sth->execute($data);	
	}
	
	public function addNewestAssignee($username)
    {
        $sql2 = "SELECT id FROM tugas ORDER BY last_mod DESC LIMIT 1";
		$this->_setSql($sql2);
		
		$result = $this->getRow();
		
		
        if (empty($result))
        {
            return false;
        }
		
		$this->addAssignee($result["id"],$username);
    }
	
	public function addTag ($id_tugas,$tag)
	{
		$sql="INSERT INTO `tags`(`id_tugas`, `tag`) VALUES (?,?); ";
		$data = array(
			$id_tugas,
			$tag
		);
		
		$sth = $this->_db->prepare($sql);
		return $sth->execute($data);	
	}
	
	public function addNewestTag($tag)
    {
        $sql2 = "SELECT id FROM tugas ORDER BY last_mod DESC LIMIT 1";
		$this->_setSql($sql2);
		
		$result = $this->getRow();
		
		
        if (empty($result))
        {
            return false;
        }
		
		$this->addTag($result["id"],$tag);
    }
	
	public function getAllTugas()
	{
		$sql = "SELECT t.id AS id, t.nama AS nama, tgl_deadline,  `status` , t.last_mod AS last_mod, pemilik, id_kategori, c.nama AS nama_kategori
				FROM categories c, tugas t WHERE c.id = t.id_kategori;";
        $this->_setSql($sql);
		
        $r = $this->getAll();
		
		
        if (empty($r))
        {
            return false;
        }
		
		return $r;
	}
	
	public function addAttachments($docId,$id_tugas)
	{
		if(isset($_FILES[$docId]))
		{
			$errors= array();
			foreach($_FILES[$docId]['tmp_name'] as $key => $tmp_name )
			{
				$file_name = $_FILES[$docId]['name'][$key];
				$file_size =$_FILES[$docId]['size'][$key];
				$file_tmp =$_FILES[$docId]['tmp_name'][$key];
				$filename = uniqid()+"."+$_FILES[$docId]['type'][$key];
				$file_type=$_FILES[$docId]['type'][$key];	
				if($file_size > 2097152){
					$errors[]='File size must be less than 2 MB';
				}		
				$sql="INSERT INTO `attachments`(`id_tugas`, `name`, `filename`, `type`) VALUES (?,?,?,?); ";
				$data = array(
					$id_tugas,
					$file_name,
					$filename,
					$file_type
				);
				
				$desired_dir="Files";
				if(empty($errors)==true){
					if(is_dir($desired_dir)==false){
						mkdir("$desired_dir", 0700);		// Create directory if it does not exist
					}
					if(is_dir("$desired_dir/".$file_name)==false){
						move_uploaded_file($file_tmp,"$desired_dir/".$filename);
					}else{									// rename the file if another one exist
						$new_dir="$desired_dir/".$filename.time();
						 rename($file_tmp,$new_dir) ;				
					}
					
				$sth = $this->_db->prepare($sql);
				$sth->execute($data);		
				}else{
						print_r($errors);
				}
			}
			if(empty($error))
			{
				echo "Success";
			}
		}
	}
	
	public function isUpdated($id_tugas, $last_request)
	{
		$sql = "SELECT COUNT(*) AS n FROM `tugas` WHERE `id` = ? AND `last_mod` > FROM_UNIXTIME(?)";
		$this->_setSql($sql);		
		$tugas = $this->getRow(array($id_tugas, $last_request));

		if ($tugas["n"] > 0) 
		{
			return false;
		}
		else
		{
			$sql = "SELECT COUNT(*) AS n FROM `comments` WHERE `id_tugas` = ? AND `time` > FROM_UNIXTIME(?)";
			$this->_setSql($sql);		
			$komentar = $this->getRow(array($id_tugas, $last_request));

			if ($komentar["n"] > 0) 
			{
				return false;
			}
			else
			{
				return true;

			}
		}
	}
	
	public function addNewestAttachments($docId)
    {
        $sql2 = "SELECT id FROM tugas ORDER BY last_mod DESC LIMIT 1";
		$this->_setSql($sql2);
		
		$result = $this->getRow();
		
		
        if (empty($result))
        {
            return false;
        }
		
		$this->addAttachments($docId,$result["id"]);
    }


	public function deleteTask($id_tugas)
	{
		$sql = 'DELETE FROM `tugas` WHERE id=?;';
		
		$data = array(
			$id_tugas
		);
		
		$sth = $this->_db->prepare($sql);
		return $sth->execute($data);
	}

	public function removeAssignee($id_tugas, $username)
	{
		$sql = 'DELETE FROM `assignees` WHERE `id_tugas` = ? AND `username` = ?;';
		
		$data = array($id_tugas, $username);
		
		$sth = $this->_db->prepare($sql);
		return $sth->execute($data);
	}					(id, nama, tgl_deadline, status, pemilik, id_kategori)
					(?, ?, ?, ?, ?, ?);";
			$this->_taskname,
			$this->_tgl_deadline,
			$this->_pemilik,
			$this->_id_kategori
        );
         
        $sth = $this->_db->prepare($sql);
        return $sth->execute($data);
    }
    
	public function updateTimestamp($id_tugas)
	{
		$sql = "UPDATE `tugas` SET `last_mod` = CURRENT_TIMESTAMP WHERE `tugas`.`id` = ?";
		 
		$data = array($id_tugas);
		 
		$sth = $this->_db->prepare($sql);
		return $sth->execute($data);
	}


	public function getSuggestionAssignees($id_tugas, $start, $limit)
	{
		$sql = "SELECT `username`, CONCAT(`username`, ' - ', `full_name`) AS `display` 
				FROM `users` 
				WHERE `username` NOT IN (SELECT `username` FROM assignees WHERE `id_tugas`=?)
				AND `username` LIKE ?
				LIMIT 0, $limit";
		$this->_setSql($sql);
		return $this->getAll(array($id_tugas, $start));

		/*
		$sql = "SELECT `username`, CONCAT(`username`, ' - ', `full_name`) AS `display` FROM `users` 
				WHERE `username` NOT IN (SELECT `username` FROM assignees WHERE `id_tugas`=?
					UNION
					SELECT `pemilik` AS `username` FROM `tugas` WHERE `id`=?)
				AND `username` LIKE CONCAT(?, '%')
				LIMIT 0, $limit";
		$this->_setSql($sql);
		return $this->getAll(array($id_tugas, $id_tugas, $start));
		*/
	}

	public function getSuggestionTags($id_tugas, $start, $limit)
	{
		$sql = "SELECT `tag` 
				FROM `tags` 
				WHERE `id_tugas` <> ?
				AND `tag` LIKE ?
				LIMIT 0, $limit";
		$this->_setSql($sql);
		return $this->getAll(array($id_tugas, $start));
	}

	public function removeTag($id_tugas, $tag)
	{
		$sql = 'DELETE FROM `tags` WHERE `id_tugas`=? AND `tag`=?;';
		
		$data = array($id_tugas, $tag);
		
		$sth = $this->_db->prepare($sql);
		return $sth->execute($data);
	}

	public function updateDeadline($id_tugas, $tgl_deadline)
	{
		$sql = 'UPDATE `tugas` SET `tgl_deadline` = ? WHERE `tugas`.`id` = ?;';
		
		$data = array($tgl_deadline, $id_tugas);
		
		$sth = $this->_db->prepare($sql);
		return $sth->execute($data);
	}

	public function isPriviledge($id_tugas, $username)
	{
		$sql = 'SELECT COUNT(*) AS `n` FROM `tugas` t, `assignees` a WHERE t.`id` = ? AND t.`id` = a.`id_tugas` AND (t.`pemilik` = ? OR a.`username` = ?)';
		$this->_setSql($sql);
		$priv = $this->getRow(array($id_tugas, $username, $username));
		if ($priv["n"] > 0) 
		{
			return true;
		}
		else
		{
			return false;
		}
	}


	public function searchTitle($q, $x, $n)
	{

		$sql = "SELECT id, nama
				FROM categories
				WHERE nama LIKE '".$q."%'			
				LIMIT ".$x." , ".$n."";
				
        $this->_setSql($sql);
		
        $r = $this->getAll();
		
		
        if (empty($r))
        {
            return false;
        }
		
		return $r;
	}
	
	public function searchTask($q, $x, $n)
	{
				
		$sql = "SELECT id, nama, tgl_deadline, status, pemilik, tag
				FROM tugas LEFT JOIN tags ON tugas.id = tags.id_tugas
				WHERE nama LIKE '".$q."%'		
				LIMIT ".$x." , ".$n."";
				
        $this->_setSql($sql);
		
        $r = $this->getAll();
		
		
        if (empty($r))
        {
            return false;
        }
		
		return $r;
	}	

	public function countTuple($q, $x, $n)
	{
		$sql = "SELECT id, count(tag) as ntag
				FROM tugas LEFT JOIN tags ON tugas.id = tags.id_tugas
				WHERE nama LIKE '".$q."%'		
				GROUP BY id
				LIMIT ".$x." , ".$n."			
				";

				
        $this->_setSql($sql);
		
        $r = $this->getAll();
		
		
        if (empty($r))
        {
            return false;
        }
		
		return $r;	
	}}
