window.onload = function(){
    new JsDatePick({
       useMode:2,
       target:"inputDeadline",
       dateFormat:"%d-%M-%Y",
       cellColorScheme:"aqua"
       /*selectedDate:{				This is an example of what the full configuration offers.
        day:5,						For full documentation about these settings please see the full version of the code.
        month:9,
        year:2006
        },
        yearsRange:[1978,2020],
        limitToToday:false,
        cellColorScheme:"beige",
        dateFormat:"%m-%d-%Y",
        imgPath:"img/",
        weekStartDay:1*/
    });
};

function validate()
{
    var input = document.getElementById("namaTugas");
    var letters = /^[0-9a-zA-Z ]+$/;
    if(input.value.match(letters) && input.value.length <= 25)
    {
        //document.getElementById("error").innerHTML=inputtxt.length.;
        return true;
       
    }
    else
    {
        alert('Tidak boleh mengandung karakter khusus dan panjang maksimal 25');
        return false;
    }
}

//smooth scroll effect
function currentYPosition() {
    if(self.pageYOffset) return pageYOffset;
    if(document.documentElement && document.documentElement.scrollTop)
        return document.documentElement.scrollTop;
    if(document.body.scrollTop)
        return document.body.scrollTop;
    
    return 0;
}

function elementYPosition(id) {
    var elm = document.getElementById(id);
    var y = elm.offsetTop;
    var node = elm;
    while(node.offsetParent && node.offsetParent != document.body) {
        node = node.offsetParent;
        y += node.offsetTop;
    }
    return y;
}

function smoothScroll(id) {
    var startY = currentYPosition();
    var stopY = elementYPosition(id);
    var distance = stopY > startY ? stopY - startY : startY - stopY;
    if(distance < 100) {
        scrollTo(0, stopY);
        return;
    }
    var speed = Math.round(distance/100);
    if(speed >= 20) speed = 20;
    var step = Math.round(distance/100);
    var leapY = stopY > startY ? startY + step : startY - step;
    var timer = 0;
    if(stopY > startY) {
        for(var i=startY; i < stopY; i+=step) {
            setTimeout("window.scrollTo(0, "+leapY+")", timer*speed);
            leapY += step; if (leapY > stopY) leapY = stopY; timer++;
        }
        return;
    }
    
    for(var i=startY; i>stopY; i-=step) {
        setTimeout("window.scrollTo(0, "+leapY+")", timer * speed);
        leapY -= step; if (leapY < stopY) leapY = stopY; timer++;
    }
}