var ajaxku;
function tambahkomentar(pilihan){
	ajaxku = buatajax();
	var url="tambahkomentar.php";
	url=url+"?kodetugas="+<?php echo $kodetugas;?>;
	url=url+"&kodeuser="+<?php echo $kodeuser;?>;
	url=url+"&komentar="+pilihan;
	url=url+"&sid="+Math.random();
	ajaxku.onreadystatechange=stateChanged;
	ajaxku.open("GET",url,true);
	ajaxku.send(null);
}
function buatajax(){
	if (window.XMLHttpRequest){
		return new XMLHttpRequest();
	}
	if (window.ActiveXObject){
		return new ActiveXObject("Microsoft.XMLHTTP");
	}
	return null;
}

function stateChanged(){
	var data;
	if (ajaxku.readyState==4){
		data=ajaxku.responseText;
		//alert(data);
		if(data.length>0){
			rincian.innerHTML = data;
		}else{
			//document.getElementById("kategori").value = "";
		}
	}
}
</script>

<script>
var ajaxku2;
function proses(pilihan){
	ajaxku2 = buatajax2();
	var url="selesaitidakrincian.php";
	url=url+"?value="+pilihan;
	url=url+"&sid="+Math.random();
	ajaxku2.onreadystatechange=stateChanged2;
	ajaxku2.open("GET",url,true);
	ajaxku2.send(null);
}
function buatajax2(){
	if (window.XMLHttpRequest){
		return new XMLHttpRequest();
	}
	if (window.ActiveXObject){
		return new ActiveXObject("Microsoft.XMLHTTP");
	}
	return null;
}

function stateChanged2(){
	var data2;
	if (ajaxku2.readyState==4){
		data2=ajaxku2.responseText;
		if(data2.length>0){
			rincian.innerHTML=data2;
		}else{
		}
	}
}

var ajaxku2;
function proses(pilihan){
	ajaxku2 = buatajax2();
	var url="selesaitidakrincian.php";
	url=url+"?value="+pilihan;
	url=url+"&sid="+Math.random();
	ajaxku2.onreadystatechange=stateChanged2;
	ajaxku2.open("GET",url,true);
	ajaxku2.send(null);
}
function buatajax2(){
	if (window.XMLHttpRequest){
		return new XMLHttpRequest();
	}
	if (window.ActiveXObject){
		return new ActiveXObject("Microsoft.XMLHTTP");
	}
	return null;
}

function stateChanged2(){
	var data2;
	if (ajaxku2.readyState==4){
		data2=ajaxku2.responseText;
		if(data2.length>0){
			rincian.innerHTML=data2;
		}else{
		}
	}
}