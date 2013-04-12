function toggle_visibility(id) {
   var e = document.getElementById(id);
   if(e.style.display == 'block')
      e.style.display = 'none';
   else
      e.style.display = 'block';
}

function finishTask(i) {
   if (i == 1) {
      document.getElementById("curtask1").style.opacity = "0";
      document.getElementById("curtask2").style.top = "20px";
      document.getElementById("curtask3").style.top = "200px";
      document.getElementById("curtask4").style.top = "380px";
      document.getElementById("curtask5").style.top = "560px";
   }
		
   else {
      if (i == 2) {
         document.getElementById("curtask2").style.opacity = "0";
         document.getElementById("curtask3").style.top = "200px";
         document.getElementById("curtask4").style.top = "380px";
         document.getElementById("curtask5").style.top = "560px";
      }
      else {
         if (i==3) {
            document.getElementById("curtask3").style.opacity = "0";
            document.getElementById("curtask4").style.top = "380px";
            document.getElementById("curtask5").style.top = "560px";
         }
         else if (i == 4) {
            document.getElementById("curtask4").style.opacity = "0";
            document.getElementById("curtask5").style.top = "560px";
         }
         else if (i == 5){
            document.getElementById("curtask5").style.opacity = "0";
         }
      }
   }
}