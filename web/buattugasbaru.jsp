<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
						"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<link id="style" rel="stylesheet" type="text/css" href="css/style.css"></link>
	
	<title>tambahkan tugas</title>
	
</head>
<body>
	<div id="logo">
			<img src="images/image.jpg" alt="logogambar" width="100" height="75"/>
			<a href="dashboard.html"><img src="images/logo1.jpg" alt="logo" width="100" height="30"/></a>
		</div>
	
		<div class="header">
			<ul id="header">
				<li ><a href="dashboard.html">home</a></li>
				<li ><a href="halamanprofil.html">profile</a></li>
				<li ><a href="halamanutama.html">logout</a></li>
				<li><input type="text" id="search" value="search.."/></li>
			</ul>
		</div>

	<div class="content">
			<div >
			<label for="namatugasbaru">Nama Tugas :</label>
			<input type="text" id="namatugasbaru" value=""/>
			
		</div>
		<div >
			<label for="attachment">Attachment:</label>
			<input type="file" id="attachment" value=""/>
		</div>
		<div>
                <label for="deadline">Deadline</label>
                <select name="date">
				  <option value="1">1</option>
				  <option value="2">2</option>
				  <option value="3">3</option>
				  <option value="4">4</option>
				  <option value="5">5</option>
				  <option value="6">6</option>
				  <option value="7">7</option>
				  <option value="8">8</option>
				  <option value="9">9</option>
				  <option value="10">10</option>
				  <option value="11">11</option>
				  <option value="12">12</option>
				  <option value="13">13</option>
				  <option value="14">14</option>
				  <option value="15">15</option>
				  <option value="16">16</option>
				  <option value="17">17</option>
				  <option value="18">18</option>
				  <option value="19">19</option>
				  <option value="20">20</option>
				  <option value="21">21</option>
				  <option value="22">22</option>
				  <option value="23">23</option>
				  <option value="24">24</option>
				  <option value="25">25</option>
				  <option value="26">26</option>
				  <option value="27">27</option>
				  <option value="28">28</option>
				  <option value="29">29</option>
				  <option value="30">30</option>
				  <option value="31">31</option>
				</select>
				<select name="month">
				  <option>1</option>
				  <option>2</option>
				  <option>3</option>
				  <option>4</option>
				  <option>5</option>
				  <option>6</option>
				  <option>7</option>
				  <option>8</option>
				  <option>9</option>
				  <option>10</option>
				  <option>11</option>
				  <option>12</option>
				</select>
				<select name="year">
				  <option>1955</option>
				  <option>1956</option>
				  <option>1957</option>
				  <option>1958</option>
				  <option>1959</option>
				  <option>1960</option>
				  <option>1961</option>
				  <option>1962</option>
				  <option>1963</option>
				  <option>1964</option>
				  <option>1965</option>
				  <option>1966</option>
				  <option>1967</option>
				  <option>1968</option>
				  <option>1969</option>
				  <option>1970</option>
				  <option>1971</option>
				  <option>1972</option>
				  <option>1973</option>
				  <option>1974</option>
				  <option>1975</option>
				  <option>1976</option>
				  <option>1977</option>
				  <option>1978</option>
				  <option>1979</option>
				  <option>1980</option>
				  <option>1981</option>
				  <option>1982</option>
				  <option>1983</option>
				  <option>1984</option>
				  <option>1985</option>
				  <option>1986</option>
				  <option>1987</option>
				  <option>1988</option>
				  <option>1989</option>
				  <option>1990</option>
				  <option>1991</option>
				  <option>1992</option>
				  <option>1993</option>
				  <option>1994</option>
				  <option>1995</option>
				  <option>1996</option>
				  <option>1997</option>
				  <option>1998</option>
				  <option>1999</option>
				  <option>2000</option>
				  <option>2001</option>
				  <option>2002</option>
				  <option>2003</option>
				  <option>2004</option>
				  <option>2005</option>
				  <option>2006</option>
				  <option>2007</option>
				  <option>2008</option>
				  <option>2009</option>
				  <option>2010</option>
				  <option>2011</option>
				  <option>2012</option>
				  <option>2013</option>
				</select>
            </div>
		<div>
			<label for="asignee">Asignee:</label>
			<input type="text" id="asignee" value=""/>
		</div>
		<div >
			<label for="tag">Tag:</label>
			<input type="text" id="tag" value=""/>
		</div>
		
		<div class="Save">
			<input type="button" value="OK" onClick="addRincianTugas()"></input>
		
		</div>
			
	</div>
	
		
	
	<script language="javascript">
		function addRincianTugas(){
				var nama_tugas = document.getElementById('namatugasbaru').value;
				var attachment_tugas =document.getElementById('attachment').value;
				var deadline_tugas = document.getElementById('deadline').value;
				var asignee_tugas =document.getElementById('asignee').value;
				var tag_tugas = document.getElementById('tag').value;
				
				if(namatugas_validation(nama_tugas)){
					if(attachmenttugas_validation(attachment_tugas)){
					window.location="progin.html";
				}
			}
			
		}
		function namatugas_validation(nama_tugas){
			var namatugas_len = nama_tugas.length;
			var letters =/^[A-Za-z]+$/;
			if(namatugas_len<=25&&nama_tugas.match(letters) ){
				return true;
			}else{
				alert('harus alfabet dan angka');
				nama_tugas.focus();
				return false;
			}
			
		}
		function attachmenttugas_validation(attachment_tugas){
			var validfileex =[".jpg",".mp4",".png",".txt",".pdf"];
			var ext = attachment_tugas.substring(attachment_tugas.lastIndexOf('.')+1);
			if(ext=="jpg"||ext=="mp4"||ext=="png"||ext=="txt"||ext=="pdf"){
				return true;
			}else{
				alert('salah upload file');
				return false;
			}
		}
	</script>
	<div class="footer">
	</div>
	<a href="#" class="overlay" id="add_task"></a>
	<div class="popup">
	
	</div>
</body>
</html>
