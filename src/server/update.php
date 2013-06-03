<?php
	function update($nama_tugas,$status,$username)
	{
		query2("UPDATE tugas INNER JOIN kategori INNER JOIN mengerjakan ON tugas.id_kategori = kategori.id_kategori AND tugas.id_tugas = mengerjakan.id_tugas SET tugas.status = :status, mengerjakan.status_tugas = :status WHERE mengerjakan.username = :username AND tugas.nama_tugas = :nama_tugas", array('status' => $status, 'username' => $username, 'nama_tugas' => $nama_tugas));
		echo json_encode("success");
	}
?>