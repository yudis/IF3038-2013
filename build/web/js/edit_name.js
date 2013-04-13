/* edit_name.js*/
function toggle_visibility(id) {
	var e = document.getElementById(id);
	if(e.style.display == 'block')
		e.style.display = 'none';
	else
		e.style.display = 'block';
}



function edit_name(){
	var xmlhttp;
	var nama = document.getElementById("new_name").value;
	
	if(window.XMLHttpRequest){
	// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	} else {
	// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			var response = xmlhttp.responseText;
			if (response !==""){
				alert(response);
				var result = eval('('+xmlhttp.responseText+')');
				var container = document.getElementById('category_item');
				container.innerHTML='';
				for(var i=0;i<2;i++){
					container.innerHTML = container.innerHTML+'<li><a href="#" onclick="get_taskkategorijs('+result[i]['cat_name']+')" >' +result[i]['cat_name']+'</a>'+
                              '<img src="../img/no.png" id="del_cat" onclick="deletecat('+result[i]['cat_name']+')" class="task_done_button" alt="" />'+
					'</li>';
				}
			} else {
			}
		} else {
		}
      }
     xmlhttp.open("GET","edit_name.php?t="+nama,true);
     xmlhttp.send();
}