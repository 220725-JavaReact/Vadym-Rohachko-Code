const verticalActiveMenuLinks = document.getElementsByClassName('vertical-menu__link_active');
// vertical menu interactive
const verticalMenuLinks = document.getElementsByClassName('vertical-menu__link');
// buttons on cards
const cards = document.getElementsByClassName('card__button');
//const unregCards = document.getElementsByName('unreg');
//const regCards = document.getElementsByName('reg');
// container of cards
const cardsContainer = document.getElementsByClassName('content');

Array.from(verticalMenuLinks).forEach((link, index) => {
	// mark button as active
	link.addEventListener('click', function onClick() {
		[].forEach.call(verticalMenuLinks, function toggleButtonColor(el) {
			el.classList.remove("vertical-menu__link_active");
		});
		link.classList.add('vertical-menu__link_active');

		// filter by categories
		Array.from(cardsContainer[0].children).map(element => {
			element.classList.remove('card_display_none');
			if (element.lastElementChild.innerText !== '1' && index === 1) {
				element.classList.add('card_display_none');
			} else if (element.lastElementChild.innerText !== '2' && index === 2) {
				element.classList.add('card_display_none');
			} else if (element.lastElementChild.innerText !== '3' && index === 3) {
				element.classList.add('card_display_none');
			}
		});
	})
});


// ✅ addEventListener to all cards
for (const card of cards) {
	card.addEventListener('click', (e) => {
		card.classList.toggle('card__button_bgcolor_red');
		if (card.classList.contains('card__button_bgcolor_red')) {
			card.innerHTML = "Remove from Cart";
		} else {
			card.innerHTML = "Add to Cart";
		}

	});
	card.addEventListener('click', (e) => {		
		e.preventDefault();

		var categoryId = Array.from(card.parentNode.parentNode.children).filter(elem => elem.matches(".card__category"))[0].defaultValue;
		var productId = Array.from(card.parentNode.parentNode.children).filter(elem => elem.matches(".card__product"))[0].defaultValue
		//console.log(Array.from(card.parentNode.parentNode.children).filter(elem => elem.matches(".card__category"))[0].defaultValue);
		//console.log(Array.from(card.parentNode.parentNode.children).filter(elem => elem.matches(".card__product"))[0].defaultValue);

		$.post("/webstore/cart", {
			categoryId: categoryId,
			productId: productId
		}).done(function() {
			console.log("Done");
		}).fail(function() {
			console.log("Fail");
		});
	});
}


/*
// ✅ addEventListener to all cards
for (const card of regCards) {
	card.addEventListener('click', (e) => {
		card.classList.toggle('card__button_bgcolor_red');
		if (card.classList.contains('card__button_bgcolor_red')) {
			card.innerHTML = "Remove from Cart_";
		} else {
			card.innerHTML = "Add to Cart_";
		}

	});
	
	card.addEventListener('click', (e) => {		
		e.preventDefault();

		var categoryId = Array.from(card.parentNode.parentNode.children).filter(elem => elem.matches(".card__category"))[0].defaultValue;
		var productId = Array.from(card.parentNode.parentNode.children).filter(elem => elem.matches(".card__product"))[0].defaultValue
		//console.log(Array.from(card.parentNode.parentNode.children).filter(elem => elem.matches(".card__category"))[0].defaultValue);
		//console.log(Array.from(card.parentNode.parentNode.children).filter(elem => elem.matches(".card__product"))[0].defaultValue);

		$.post("/webstore/regist", {
			categoryId: categoryId,
			productId: productId
		}).done(function() {
			console.log("Done");
		}).fail(function() {
			console.log("Fail");
		});
	});
}

// ✅ addEventListener to all cards
for (const card of unregCards) {
	card.addEventListener('click', (e) => {
		card.classList.toggle('card__button_bgcolor_red');
		if (card.classList.contains('card__button_bgcolor_red')) {
			card.innerHTML = "_Remove from Cart";
		} else {
			card.innerHTML = "_Add to Cart";
		}

	});
	card.addEventListener('click', (e) => {		
		e.preventDefault();

		var categoryId = Array.from(card.parentNode.parentNode.children).filter(elem => elem.matches(".card__category"))[0].defaultValue;
		var productId = Array.from(card.parentNode.parentNode.children).filter(elem => elem.matches(".card__product"))[0].defaultValue
		//console.log(Array.from(card.parentNode.parentNode.children).filter(elem => elem.matches(".card__category"))[0].defaultValue);
		//console.log(Array.from(card.parentNode.parentNode.children).filter(elem => elem.matches(".card__product"))[0].defaultValue);

		$.post("/webstore/unregist", {
			categoryId: categoryId,
			productId: productId
		}).done(function() {
			console.log("Done");
		}).fail(function() {
			console.log("Fail");
		});
	});
}
*/




