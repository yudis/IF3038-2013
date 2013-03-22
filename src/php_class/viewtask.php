<?php
  
  
  class ViewTask {
	
	public $task;
	public $attach;
	public $tag;
	public $assignee;
	
	/*
	 * - tugas UDAH
	 * - att UDAH
	 * - tag
	 * - komentar
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
  }
  
  
?>