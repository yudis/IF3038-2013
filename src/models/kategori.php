<?php

class Kategori extends Model
{
    private $_id;
	private $_nama;
    private $_last_mod;
	private $_tugas;
	
	public function get_id() { return $this->_id; } 
	public function get_nama() { return $this->_nama; } 
	public function get_last_mod() { return $this->_last_mod; } 
	public function get_tugas() { return $this->_tugas; } 
	public function get_tugas2($i) { return $this->_tugas[$i]; } 
	public function set_id($x) { $this->_id = ($x); } 
	public function set_nama($x) { $this->_nama = $x; } 
	public function set_last_mod($x) { $this->_last_mod = $x; } 
	public function set_tugas($x) { $this->_tugas = $x; } 
	public function set_tugas2($i,$x) { $this->_tugas[$i] = $x; } 
	
	public function fromArray($kategori)
	{
		$this->_id = $kategori["id"];
		$this->_nama = $kategori["nama"];
		$this->_tugas = $kategori["tugas"];
	}
	
	public function toArray()
	{
		$kategori = array();
		$kategori["id"] = $this->_id;
		$kategori["nama"] = $this->_nama;
		$kategori["tugas"] = $this->_tugas;
		$kategori["last_mod"] = $this->_last_mod;
		
		return $kategori;
	}
	
	public function getKategori($id_kategori)
    {
        $sql = "SELECT * FROM kategori WHERE id=? ";
        $this->_setSql($sql);
		
        $kategori = $this->getRow(array($id_kategori));
         
        if (empty($kategori))
        {
            return false;
        }
		
		$this->_id = $kategori["id"];
		$this->_nama = $kategori["nama"];
		$this->_last_mod = $kategori["last_mod"];
		$this->getTugas($id_kategori);
        return $this->toArray();
    }
	
	public function getTugas ($id_kategori)
	{
		$sql = "SELECT id_tugas FROM categories WHERE id_kategori=? ";
        $this->_setSql($sql);
		
        $result = $this->getAll(array($id_kategori));
		
		
        if (empty($result))
        {
            return false;
        }
		
		
		$i=0;
		while(!empty($result[$i]["id_tugas"]))
		{
			$sql = "SELECT nama FROM tugas WHERE id=? ";
			$this->_setSql($sql);
			
			$result2 = $this->getRow(array($result[$i]["id_tugas"]));
         
			if (empty($result2))
			{
				return false;
			}
			$this->_tugas[$i]=$result2["nama"];
			
			$i++;
		}
	}
	
    public function store()
    {
        $sql = "INSERT INTO kategori
                    (id, nama, last_mod)
                VALUES 
                    (?, ?, ?);";
         
        $data = array(
			$this->_id,
			$this->_nama,
			$this->_last_mod
        );
         
        $sth = $this->_db->prepare($sql);
        return $sth->execute($data);
    }
}
?>