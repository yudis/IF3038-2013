<?php
  
  
  class CreateTask {
	
	public $allowedExts;
	public $namafile;
	public $folder;
	public $path;
	
	public $POS;
	public $FILE;
	
	public function CreateTask ($x, $y) {
	  
	  $this->POS = $x;
	  $this->FILE = $y;
	  $this->allowedExts = array("jpg", "jpeg", "gif", "png");
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
	  
	}
  }
  
  
?>