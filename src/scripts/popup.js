/**
 * Display an HTML Pop-Up
 * Original Soruce: http://www.pat-burt.com/web-development/how-to-do-a-css-popup-without-opening-a-new-window/
 * Modified for College Task (IF3094 - Pemrograman Internet)
 * IF-ITB 2013
 */

function toggle_display(elmt_id) {
    var element = document.getElementById(elmt_id);
    if (element.style.display == 'none' || element.style.display == '') {
        element.style.display = 'block';
    } else {
        element.style.display = 'none';
    }
}

function blanket_size(content, blanket, height) {
    if (typeof window.innerWidth != 'undefined') {
        viewportheight = window.innerHeight;
    } else {
        viewportheight = document.documentElement.clientHeight;
    }
    if ((viewportheight > document.body.parentNode.scrollHeight) && (viewportheight > document.body.parentNode.clientHeight)) {
        blanket_height = viewportheight;
    } else {
        if (document.body.parentNode.clientHeight > document.body.parentNode.scrollHeight) {
            blanket_height = document.body.parentNode.clientHeight;
        } else {
            blanket_height = document.body.parentNode.scrollHeight;
        }
    }
    var blanket_elmnt = document.getElementById(blanket);
    blanket_elmnt.style.height = blanket_height + 'px';
    var popUpDiv = document.getElementById(content);
    popUpDiv_height=(blanket_height-height)/2;
    popUpDiv.style.top = popUpDiv_height + 'px';
}

function popup_pos(content, width) {
    if (typeof window.innerWidth != 'undefined') {
        viewportwidth = window.innerHeight;
    } else {
        viewportwidth = document.documentElement.clientHeight;
    }
    if ((viewportwidth > document.body.parentNode.scrollWidth) && (viewportwidth > document.body.parentNode.clientWidth)) {
        window_width = viewportwidth;
    } else {
        if (document.body.parentNode.clientWidth > document.body.parentNode.scrollWidth) {
            window_width = document.body.parentNode.clientWidth;
        } else {
            window_width = document.body.parentNode.scrollWidth;
        }
    }
    var popUpDiv = document.getElementById(content);
    window_width=(window_width-width)/2;
    popUpDiv.style.left = window_width + 'px';
}

function popup(content, blanket, height, width) {
    blanket_size(content, blanket, height);
    popup_pos(content, width);
    toggle_display(blanket);
    toggle_display(content);
    
    return false;
}