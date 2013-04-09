<?php
  function diffDate($date1,$date2){
		$diff = abs(strtotime($date2) - strtotime($date1)); 

		$years   = floor($diff / (365*60*60*24)); 
		$months  = floor(($diff - $years * 365*60*60*24) / (30*60*60*24)); 
		$days    = floor(($diff - $years * 365*60*60*24 - $months*30*60*60*24)/ (60*60*24));
		$hours   = floor(($diff - $years * 365*60*60*24 - $months*30*60*60*24 - $days*60*60*24)/ (60*60)); 
		$minutes  = floor(($diff - $years * 365*60*60*24 - $months*30*60*60*24 - $days*60*60*24 - $hours*60*60)/ 60); 
		$seconds = floor(($diff - $years * 365*60*60*24 - $months*30*60*60*24 - $days*60*60*24 - $hours*60*60 - $minutes*60)); 
		
		$retval="";
		if ($years!=0){
			$retval=$retval.$years." tahun ";
		}else if ($months!=0&&$years==0){
			$retval=$retval.$months." bulan ";
		}else if ($days!=0&&$months==0){
			$retval=$retval.$days." hari ";
		}else if ($hours!=0&&$days==0){
			$retval=$retval.$hours." jam ";
		}else if ($minutes!=0&&$hours==0){
			$retval=$retval.$minutes." menit ";
		}else if ($seconds!=0&&$minutes==0){
			$retval=$retval.$seconds." detik";
		}
		$retval=$retval." yang lalu";
		
		return $retval;
	}
	
	function diffHari($date1,$date2){
		$diff = abs(strtotime($date2) - strtotime($date1)); 
		$days = floor(($diff - $years * 365*60*60*24 - $months*30*60*60*24)/ (60*60*24));
		return $days;
	}
?>
