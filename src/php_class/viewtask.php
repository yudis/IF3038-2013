<?php
  
  
  class ViewTask {
	
	public $task;
	public $attach;
	public $tag;
	public $assignee;
	public $komen;
	public $komentator;
	
	/* VIEW
	 * - tugas UDAH
	 * - att UDAH
	 * - tag UDAH
	 * - komentar
	 *
	 * EDIT
	 *
	 *
	 *
	 *
	 * 
	 */
	
	public function ViewTask ($x) {
	  
	  // AMBIL tugas
	  $sql = "SELECT * FROM tugas WHERE idtugas='".$x."'";
	  $hasil = mysql_query($sql);
	  $this->task = mysql_fetch_array($hasil);
	  
	  // AMBIL attachment
	  $sql = "SELECT * FROM attachment WHERE tugas_idtugas='".$x."'";
	  $hasil = mysql_query($sql);
	  $temp = "";
	  $this->attach = new ArrayObject();
	  while ($temp = mysql_fetch_array($hasil)) {
		$this->attach->append($temp);
	  }
	  
	  // AMBIL assignee
	  $sql = "SELECT * FROM accounts_has_tugas AS b, accounts AS a
			  WHERE b.tugas_idtugas='".$x."'
			  AND b.accounts_idaccounts = a.idaccounts";
	  $hasil = mysql_query($sql);
	  $temp = "";
	  $this->assignee = new ArrayObject();
	  while ($temp = mysql_fetch_array($hasil)) {
		$this->assignee->append($temp);
	  }
	  
	  // AMBIL tag
	  $sql = "SELECT * FROM tugas_has_tag AS b, tag AS a
			  WHERE b.tugas_idtugas='".$x."'
			  AND b.tag_idtag = a.idtag";
	  $hasil = mysql_query($sql);
	  $temp = "";
	  $this->tag = new ArrayObject();
	  while ($temp = mysql_fetch_array($hasil)) {
		$this->tag->append($temp);
	  }
	  
	  /*
	   *
	   *sampai pada orderby
	   *
	   */
	  // AMBIL komen & komentator
	  $sql = "SELECT * FROM komentar	
			  WHERE tugas_idtugas='".$x."'
			  ORDER BY created";
	  $hasil = mysql_query($sql);
	  $temp = "";
	  $this->komen = new ArrayObject();
	  $this->komentator = new ArrayObject();
	  while ($temp = mysql_fetch_array($hasil)) {
		$this->komen->append($temp);
		// Ambil Komentator
		$sql2 = "SELECT * FROM accounts	
				WHERE idaccounts='".$temp['accounts_idaccounts']."'";
		$hasil2 = mysql_query($sql2);
		$temp2 = mysql_fetch_array($hasil2);
		$this->komentator->append($temp2);
	  }
	}
	
	public function getTask() {
	  return $this->task;
	}
	
	public function getAttachment() {
	  return $this->attach;
	}
	
	public function getAssignee() {
	  return $this->assignee;
	}
	
	public function getTag() {
	  return $this->tag;
	}
	
	public function getKomen() {
	  return $this->komen;
	}
	
	public function getKomentator() {
	  return $this->komentator;
	}
  }
  
  
?>