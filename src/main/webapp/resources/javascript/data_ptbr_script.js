/* Data PT-BR primefaces */
PrimeFaces.locales['pt'] = {
	closeText: 'Fechar',
	prevText: 'Anterior',
	nextText: 'Próximo',
	currentText: 'Começo',
	monthNames: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
	monthNamesShort: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],
	dayNames: ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado'],
	dayNamesShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb'],
	dayNamesMin: ['D', 'S', 'T', 'Q', 'Q', 'S', 'S'],
	weekHeader: 'Semana',
	firstDay: 0,
	isRTL: false,
	showMonthAfterYear: false,
	yearSuffix: '',
	timeOnlyTitle: 'Só Horas',
	timeText: 'Tempo',
	hourText: 'Hora',
	minuteText: 'Minuto',
	secondText: 'Segundo',
	ampm: false,
	month: 'Mês',
	week: 'Semana',
	day: 'Dia',
	allDayText: 'Todo o Dia'
};

//<![CDATA[
function dateTemplateFunc(date) {
	return '<span style="background-color:' + ((date.day < 21 && date.day > 10) ? '#81C784' : 'inherit') + ';border-radius:50%;width: 2.5rem;height: 2.5rem;line-height: 2.5rem;display: flex;align-items: center;justify-content: center;">' + date.day + '</span>';
}

    //]]>