// -----------------------------------------------------------------------------------
// http://wowslider.com/
// JavaScript Wow Slider is a free software that helps you easily generate delicious 
// slideshows with gorgeous transition effects, in a few clicks without writing a single line of code.
// Last updated: 2012-03-29
//
//***********************************************
// Obfuscated by Javascript Obfuscator
// http://javascript-source.com
//***********************************************
function ws_fade(b, a) {
	var c = jQuery;
	a.each(function(d) {
		if (d == b.startSlide) {
			c(this).show()
		} else {
			c(this).hide()
		}
	});
	this.go = function(d, e) {
		c(a.get(d)).fadeIn(b.duration);
		c(a.get(e)).fadeOut(b.duration);
		return d
	}
};// -----------------------------------------------------------------------------------
// http://wowslider.com/
// JavaScript Wow Slider is a free software that helps you easily generate
// delicious
// slideshows with gorgeous transition effects, in a few clicks without writing
// a single line of code.
// Last updated: 2012-03-29
//
// ***********************************************
// Obfuscated by Javascript Obfuscator
// http://javascript-source.com
// ***********************************************
jQuery("#wowslider-container1").wowSlider({
	effect : "fade",
	prev : "",
	next : "",
	duration : 25 * 100,
	delay : 50 * 100,
	outWidth : 1024,
	outHeight : 160,
	width : 1020,
	height : 160,
	autoPlay : true,
	stopOnHover : false,
	loop : false,
	bullets : true,
	caption : true,
	controls : true,
	logo : "engine1/loading.gif",
	images : 0
});