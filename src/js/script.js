// JavaScript Document
var TimeOut = 300;
var currentLayer = null;
var currentItem = null;
var currentLayerNum = 0;
var noClose = 0;
var closeTimer = null;

function mOpen(n) {
	var l = document.getElementById("menu" + n);
	var mm = document.getElementById("mmenu" + n);
	
	if (l) {
		//document.writeln("masuk");
		mCancelCloseTime();
		l.style.visibility = 'visible';
		
		if (currentLayer && (currentLayerNum != n)) {
			currentLayer.style.visibility = 'hidden';
		}
		currentLayer = l;
		currentItem = mm;
	} else if (currentLayer) {
		currentLayer.style.visibility = 'hidden';
		currentLayerNum = 0;
		currentItem = null;
		currentLayer = null;
	} //else document.writeln("ngga masuk mana mana");
}

function mCloseTime() {
	closeTimer = window.setTimeout(mClose, TimeOut);
}

function mCancelCloseTime() {
	//document.writeln("masuk mCancelCloseTime");
	if (closeTimer) {
		window.clearTimeout(closeTimer);
		closeTimer = null;
	}
	//document.writeln("keluar mCancelCloseTime");
}

function mClose() {
	if (currentLayer && noClose != 1) {
		currentLayer.style.visibility = 'hidden';
		currentLayerNum = 0;
		currentLayer = null;
		currentItem = null;
	} else noClose = 0;
	currentLayer = null;
	currentItem = null;
}

document.onclick = mClose();

