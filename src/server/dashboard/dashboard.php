<?php
	function ubahStatus($nomor)
	{	
		$result = query2('UPDATE tugas SET status = not status WHERE id_tugas=:id_tugas', array("id_tugas" => $nomor));
        echo json_encode('Success');        
	}
	
	function getCat()
	{
		$result = queryAll2('SELECT * from kategori');
		echo json_encode($result);
	}
	
	function getTask($username)
	{
		$result = queryAll('SELECT * FROM tugas NATURAL JOIN mengerjakan WHERE mengerjakan.username=:username', array('username' => $username));
		
		foreach($result as &$row) {
		 
			$row['cat'] = queryAll('SELECT nama_kategori FROM kategori WHERE id_kategori=:id_kategori',array('id_kategori' => $row['id_kategori']));
		}
		/*
		foreach($result as &$row)
		{
			$row['category'] = queryAll('SELECT nama_kategori FROM kategori WHERE (id_kategori=:result[id_kategori])');
		}
		*/
		echo json_encode($result);
		
		/*
		$result = queryAll2('SELECT * from tugas natural join mengerjakan');
		
		while( $result){
			$result2 = queryAll('SELECT nama_kategori FROM kategori WHERE (id_kategori=:result[id_kategori])');

	        echo json_encode("<a href=\"rinciantugas.php?id_tugas=".$row['id_tugas']."\">");
			echo json_encode("<div id=\"listtask\">");
			echo json_encode("<div>".$row['nama_tugas']."</div>");
			echo json_encode("<div>".$row['deadline']."</div>");
			echo json_encode("<div>tag: ".$row['tag']."</div>");
			if ($row['status']) {
				echo json_encode("<input type=\"checkbox\" name=\"Status\" checked onclick=\"ubahStatus(".$row['id_tugas'].")\">Selesai");
			} else {
				echo json_encode("<input type=\"checkbox\" name=\"Status\" onclick=\"ubahStatus(".$row['id_tugas'].")\">Belum Selesai");
			}
			echo json_encode("<div><button type=\"button\">Delete</button></div>");
		
			echo json_encode("<div>".$row2[0]."</div>");
			echo json_encode("</div></a>");
		}
		*/
	}
	
	function catTask($n)
	{
		$result = queryAll("SELECT * from tugas natural join mengerjakan WHERE id_kategori= :n", array("n" => $n));
		$temp=0;
		while( $result){
			$temp+=1;
	        echo json_encode("<a href=\"rinciantugas.php?id_tugas=".$row['id_tugas']."\">");
			echo json_encode("<div id=\"listtask\">");
			echo json_encode("<div>".$row['nama_tugas']."</div>");
			echo json_encode("<div>".$row['deadline']."</div>");
			echo json_encode("<div>tag: ".$row['tag']."</div>");
			
			if ($row['status']) {
				echo json_encode("<input type=\"checkbox\" name=\"Status\" checked onclick=\"ubahStatus(".$row['id_tugas'].")\">Selesai");
			} else {
				echo json_encode("<input type=\"checkbox\" name=\"Status\" onclick=\"ubahStatus(".$row['id_tugas'].")\">Belum Selesai");
			}
			
			echo json_encode("</div></a>");
		}
	}
?>