function editTask(){
	var dead = document.getElementById("atributdeadline");
	var ass = document.getElementById("atributassignee");
	var tag = document.getElementById("atributtag");
	var button = document.getElementById("edittaskbutton");

	dead.innerHTML="<select name='Tanggal'>				<option value='1' selected=''>1</option>				<option value='2'> 2</option>				<option value='3'> 3</option>				<option value='4'> 4</option>				<option value='5'> 5</option>				<option value='6'> 6</option>				<option value='7'> 7</option>				<option value='8'> 8</option>				<option value='9'> 9</option>				<option value='10' >10</option>				<option value='11' >11</option>				<option value='12' >12</option>				<option value='13' >13</option>				<option value='14' >14</option>				<option value='15' >15</option>				<option value='16' >16</option>				<option value='17' >17</option>				<option value='18' >18</option>				<option value='19' >19</option>				<option value='20' >20</option>				<option value='21' >21</option>				<option value='22' >22</option>				<option value='23' >23</option>				<option value='24' >24</option>				<option value='25' >25</option>				<option value='26' >26</option>				<option value='27' >27</option>				<option value='28' >28</option>				<option value='29' >29</option>				<option value='30' >30</option><option value='31' >31</option></select>			<select name='Bulan'>				<option value='Januari' selected=''>Januari</option>				<option value='Februari' >Februari</option>				<option value='Maret'>Maret</option>				<option value='April' >April</option>				<option value='Mei' >Mei</option>				<option value='Juni' >Juni</option>				<option value='Juli' >Juli</option>				<option value='Agustus' >Agustus</option>				<option value='September' >September</option>				<option value='Oktober' >Oktober</option>				<option value='November' >November</option>				<option value='Desember' >Desember</option>			</select>			<select name='Tahun'>				<option value='1989' selected=''>1989</option>				<option value='1990' >1990</option>				<option value='1991' >1991</option>				<option value='1992' >1992</option>				<option value='1993' >1993</option>				<option value='1994' >1994</option>				<option value='1995' >1995</option>				<option value='1996' >1996</option>				<option value='1997' >1997</option>				<option value='1998' >1998</option>				<option value='1999' >1999</option>				<option value='2000' >2000</option>				<option value='2001' >2001</option>				<option value='2002' >2002</option>				<option value='2003' >2003</option>				<option value='2004' >2004</option>				<option value='2005' >2005</option>				<option value='2006' >2006</option>				<option value='2007' >2007</option>				<option value='2008' >2008</option>			</select>";
	ass.innerHTML="<input type='text'>";
	tag.innerHTML="<input type='tag'>";
	button.value="Save Changes";
}
	
function ShowImagePreview( files ){
    if( !( window.File && window.FileReader && window.FileList && window.Blob ) )
    {
      alert('The File APIs are not fully supported in this browser.');
     return false;
    }

    if( typeof FileReader === "undefined" )
    {
        alert( "Filereader undefined!" );
        return false;
    }

    var file = files[0];

    if( !( /image/i ).test( file.type ) )
    {
        alert( "File is not an image." );
        return false;
    }

    var reader = new FileReader();
    reader.onload = function(event) 
            { var img = new Image; 
              img.onload = UpdatePreviewCanvas; 
              img.src = event.target.result;  };
    reader.readAsDataURL( file );
}

function UpdatePreviewCanvas(){
    var img = this;
    var canvas = document.getElementById( 'previewcanvas' );

    if( typeof canvas === "undefined" 
        || typeof canvas.getContext === "undefined" )
        return;

    var context = canvas.getContext( '2d' );

    var world = new Object();
    world.width = canvas.offsetWidth;
    world.height = canvas.offsetHeight;

    canvas.width = world.width;
    canvas.height = world.height;

    if( typeof img === "undefined" )
        return;

    var WidthDif = img.width - world.width;
    var HeightDif = img.height - world.height;

    var Scale = 0.0;
    if( WidthDif > HeightDif )
    {
        Scale = world.width / img.width;
    }
    else
    {
        Scale = world.height / img.height;
    }
    if( Scale > 1 )
        Scale = 1;

    var UseWidth = Math.floor( img.width * Scale );
    var UseHeight = Math.floor( img.height * Scale );

    var x = Math.floor( ( world.width - UseWidth ) / 2 );
    var y = Math.floor( ( world.height - UseHeight ) / 2 );

    context.drawImage( img, x, y, UseWidth, UseHeight );  

}

