<?php
  
  
  class CreateTask {
	
	public $allowedExts;
	public $namafile;
	public $folder;
	public $path;
	
	public $POS;
	public $FILE;
	
	// Constructor
	public function CreateTask ($x, $y) {
	  $this->POS = $x;
	  $this->FILE = $y;
	  $this->allowedExts = array("jpg", "jpeg", "gif", "png", "avi", "mp4", "flv", "3gp", "wmv");
	  $this->namafile = "";
	  $this->folder = "attachment/";
	  $this->path = "";
	}
	
	public function Create () {
	  
	  $namafile = "";
	  $folder = "attachment/";
	  $path = "";
	  $idtugas = "";
	  
	  // TAMBAH Tugas
	  $sql = "INSERT INTO tugas (nama, deadline, status_selesai, kategori_idkategori)
			  VALUES ('".$this->POS['username']."', '".$this->POS['deadline']."', 'false', '1')";
	  $result = mysql_query($sql);
	  if (!$result) {
		die('Error1: ' . mysql_error());
	  }
	  
	  // Ambil ID tugas buat ForKey Attachment
	  $sql = "SELECT LAST_INSERT_ID();";
	  $result = mysql_query($sql);
	  $idtugas = mysql_result($result,0);
	  
	  // Bikin Folder tempat Attachment
	  $folder = $this->folder . $this->POS['username'] ."-". hash("crc32b", $this->POS['username'].rand()) ."/";
	  if (!is_dir($folder)) {
		mkdir($folder);
	  }
	  
	  // Upload Attachment
	  foreach ($this->FILE as $value) {
		$extension = end(explode(".", $value["name"]));
		if ((in_array($extension, $this->allowedExts)) && ($value["name"]!=Null)) {
		  if ($value["error"] > 0) {
			  //echo "Return Code: " . $FILE["attach"]["error"] . "<br>";
		  } else {
			// PINDAH File
			$namafile = $value["name"];	
			$path = $folder . $namafile;		
			move_uploaded_file($value["tmp_name"], $path); //echo "Stored in: " . $path;
			// TAMBAH Attachment ke Database
			$sql = "INSERT INTO attachment (path, nama, tugas_idtugas)
					VALUES ('".$folder."', '".$namafile."', '".$idtugas."')";
			if (!mysql_query($sql)) {
			  die('Error2: ' . mysql_error());
			}
		  }
		} else {
		  //echo "Invalid file"; // redirect ke halaman error
		}
	  }
	  
	  // TAMBAH Tag
	  $this->TambahTag("".$idtugas);
	  
	  // TAMBAH Assignee
	  $this->TambahAssignee("".$idtugas);
	  
	  
	}
	
	private function TambahAssignee ($idtgs) {
	  $temp = split(", ",$this->POS['namasign']);
	  foreach ($temp as $y) {
		if ($y!="") {
		  $sql = "SELECT idaccounts FROM accounts WHERE username = '".$y."'";
		  $result = mysql_query($sql);
		  $fetch = mysql_fetch_array($result);  
		  $sql = "INSERT
				  INTO accounts_has_tugas (accounts_idaccounts, tugas_idtugas, pembuat)
				  VALUES ('".$fetch["idaccounts"]."','".$idtgs."',false);";
		  $result = mysql_query($sql);
		}
	  }
	}
	private function TambahTag ($idtgs) {
	  $temp = split(", ",$this->POS['tag']);
	  $fetch = "";
	  foreach ($temp as $y) {
		if ($y!="") {
		  $sql = "SELECT idtag FROM tag WHERE nama = '".$y."'";
		  $result = mysql_query($sql);
		  $fetch = mysql_fetch_array($result);
		  // Kalau tag belum ada, bikin baru
		  if ($fetch["idtag"]=="") {
			$sql = "INSERT
					INTO tag (nama)
					VALUES ('".$y."');";
			$result = mysql_query($sql);
			$sql = "SELECT idtag FROM tag WHERE nama = '".$y."'";
			$result = mysql_query($sql);
			$fetch = mysql_fetch_array($result);
		  }
		  $sql = "INSERT
				  INTO tugas_has_tag (tag_idtag, tugas_idtugas)
				  VALUES ('".$fetch["idtag"]."','".$idtgs."');";
		  $result = mysql_query($sql);
		}
	  }
	}
	public function Tes() {
	  $this->TambahTag("54");
	}
	
  /* 
   * - Tugas UDAH
   * - Assignee UDAH
   * - Attachment UDAH
   * - Tag UDAH
   * - Kategori
   * 
   */
  
	
  }
  
  
?>