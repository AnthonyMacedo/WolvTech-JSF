/**
 * MENU HAMBURGUER - TOGGLE
 */
var iconMenu = document.querySelector('.js-icon-menu');
var content = document.querySelector('.js-content');
var body = document.querySelector('body');

iconMenu.addEventListener('click', function() {
	body.classList.toggle('js-toggle');

})

/**
 *   SUBMENUS - ICON
 */
var subMenuCadastro = document.getElementsByClassName('nav-link-arrow')[0];
var subMenuReserva = document.getElementsByClassName('nav-link-arrow')[1];
var subMenuListas = document.getElementsByClassName('nav-link-arrow')[2];

var firstList = document.querySelector('.nav-bar-list-1');
var secondList = document.querySelector('.nav-bar-list-2');
var thirdList = document.querySelector('.nav-bar-list-3');

var arrow = document.querySelectorAll('.js-arrow');

var icon = document.querySelectorAll('.js-icon');

// submenu cadastro
subMenuCadastro.addEventListener('click', function() {

	firstList.classList.toggle('js-hide');

	arrow[0].classList.toggle('pi-angle-left');
	arrow[0].classList.toggle('pi-angle-down');

	icon[0].classList.toggle('pi-folder');
	icon[0].classList.toggle('pi-folder-open')


});
// submenu reservas
subMenuReserva.addEventListener('click', function() {

	secondList.classList.toggle('js-hide');

	arrow[1].classList.toggle('pi-angle-left');
	arrow[1].classList.toggle('pi-angle-down');

	icon[1].classList.toggle('pi-folder');
	icon[1].classList.toggle('pi-folder-open')


});
// submenu Listas
subMenuListas.addEventListener('click', function() {

	thirdList.classList.toggle('js-hide');

	arrow[2].classList.toggle('pi-angle-left');
	arrow[2].classList.toggle('pi-angle-down');

	icon[2].classList.toggle('pi-folder');
	icon[2].classList.toggle('pi-folder-open');

})


