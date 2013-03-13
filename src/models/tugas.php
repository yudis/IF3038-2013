<?php

class Tugas extends Model
{
    private $_id_tugas;
	private $_taskname;
	private $_attachment;
    private $_tgl_deadline;
    private $_status;
    private $_last_mod;
    private $_pemilik;
	private $_tag;
	private $_kategori;
	
	public function get_id_tugas() { return $this->_id_tugas; } 
	public function get_taskname() { return $this->_taskname; } 
	public function get_attachment() { return $this->_attachment; } 
	public function get_attachment2($i) { return $this->_attachment[$i]; } 
	public function get_tgl_deadline() { return $this->_tgl_deadline; } 
	public function get_status() { return $this->_status; } 
	public function get_last_mod() { return $this->_last_mod; } 
	public function get_pemilik() { return $this->_pemilik; } 
	public function get_kategori() { return $this->_kategori; } 
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
	public function set_tag($x) { $this->_tag = $x; } 
	public function set_tag2($i,$x) { $this->_tag[$i] = $x; } 
	
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
		$this->_kategori = $tugas["kategori"];
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
		$tugas["kategori"] = $this->_kategori;
		
		return $tugas;
	}
	
	public function getTugas($id_tugas)
    {
        $sql = "SELECT * FROM tugas WHERE id=? ";
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
		$this->getKategori ($id_tugas);
		$this->getTag ($id_tugas);
		$this->getAttachment($id_tugas);
        return $this->toArray();
    }
	
	public function getKategori ($id_tugas)
	{
		$sql = "SELECT id_kategori FROM categories WHERE id_tugas=? ";
        $this->_setSql($sql);
		
        $result = $this->getRow(array($id_tugas));
         
        if (empty($result))
        {
            return false;
        }
		$sql = "SELECT nama FROM kategori WHERE id=? ";
        $this->_setSql($sql);
		
        $result2 = $this->getRow(array($result["id_kategori"]));
         
        if (empty($result2))
        {
            return false;
        }
		$this->_kategori=$result2["nama"];
	}
	
	public function getTag ($id_tugas)
	{
		$sql = "SELECT tag FROM tags WHERE id_tugas=? ";
        $this->_setSql($sql);
		
        $r = $this->getAll(array($id_tugas));
		
		
        if (empty($r))
        {
            return false;
        }
		
		$i=0;
		while(!empty($r[$i]["tag"]))
		{
			$this->_tag = $r[$i]["tag"];
			$i++;
		}
	}
	
	public function getAttachment ($id_tugas)
	{
		$sql = "SELECT attachment FROM attachments WHERE id_tugas=? ";
        $this->_setSql($sql);
		
        $r = $this->getAll(array($id_tugas));
		
		
        if (empty($r))
        {
            return false;
        }
		
		$i=0;
		while(!empty($r[$i]["attachment"]))
		{
			$this->_tag = $r[$i]["attachment"];
			$i++;
		}
	}
	
    public function store()
    {
        $sql = "INSERT INTO tugas 
                    (id, nama, tgl_deadline, status, last_mod, pemilik)
                VALUES 
                    (?, ?, ?, ?, ?, ?);";
         
        $data = array(
			$this->_id_tugas,
			$this->_taskname,
			$this->_tgl_deadline,
			$this->_status,
			$this->_last_mod,
			$this->_pemilik
        );
         
        $sth = $this->_db->prepare($sql);
        return $sth->execute($data);
    }
}
?>