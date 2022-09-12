const verticalActiveMenuLinks = document.getElementsByClassName('vertical-menu__link_active');
// vertical menu interactive
const verticalMenuLinks = document.getElementsByClassName('vertical-menu__link');
// buttons on cards
const cards = document.getElementsByClassName('card__button');
//const unregCards = document.getElementsByName('unreg');
//const regCards = document.getElementsByName('reg');
// container of cards
const cardsContainer = document.getElementsByClassName('content');
// buttons inside Cart itself
const buttonsInCart = document.getElementsByClassName('wrapper-line__data-change');
// cart badge
const cartBadgeElement = document.getElementsByClassName('webstore-badge');

// filter categories
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


//process buttons + - in cart (adding, substracting items in cart)
for (const button of buttonsInCart) {
	button.addEventListener('click', (e) => {
		e.preventDefault();
		let flag = 1;

		if (button.value === "-") {
			flag = -1;
		} else if (button.value === "+") {
			flag = 1;
		}

		var quantity = Array.from(button.parentNode.children).filter(elem => elem.matches(".wrapper-line__quantity"))[0].innerHTML;
		//console.log(Array.from(button.parentNode.children).filter(elem => elem.matches(".wrapper-line__quantity"))[0].innerHTML);
		var itemPrice = Array.from(button.parentNode.children).filter(elem => elem.matches(".wrapper-line__price"))[0].innerHTML;
		//console.log(Array.from(button.parentNode.children).filter(elem => elem.matches(".wrapper-line__price"))[0].innerHTML);
		var itemTotalPrice = Array.from(button.parentNode.children).filter(elem => elem.matches(".wrapper-line__total"))[0];

		const grandTotalElement = document.getElementsByClassName('wrapper-line__grand-total');
		let grandTotal = grandTotalElement[0].lastElementChild.innerHTML;

		let categoryId = button.dataset.categoryId;
		let productId = button.dataset.productId;

		if (parseInt(quantity, 10) + flag > 0) {
			// item total price
			itemTotalPrice.innerHTML = (parseFloat(itemTotalPrice.innerHTML, 10) + parseFloat(itemPrice, 10) * flag).toFixed(2);
			// all items total price
			grandTotalElement[0].lastElementChild.innerText = (parseFloat(grandTotal, 10) + parseFloat(itemPrice, 10) * flag).toFixed(2);
			// item total quantity
			Array.from(button.parentNode.children)
				.filter(elem => elem.matches(".wrapper-line__quantity"))[0]
				.innerHTML = parseInt(quantity, 10) + flag;

			console.log("JS category id = " + button.dataset.categoryId);
			console.log("JS product id = " + button.dataset.productId);		

			$.post("/webstore2/cart", {
				categoryId: categoryId,
				productId: productId,
				orderedQty: 1 * flag
			}).done(function() {
				console.log("Done");
			}).fail(function() {
				console.log("Fail");
			});

		} else {

			$.post("/webstore2/cart", {
				categoryId: categoryId,
				productId: productId,
				orderedQty: 1 * flag
			}).done(function() {
				console.log("Done");
			}).fail(function() {
				console.log("Fail");
			});			

			// item total price
			itemTotalPrice.innerHTML = (parseFloat(itemTotalPrice.innerHTML, 10) + parseFloat(itemPrice, 10) * flag).toFixed(2);
			// all items total price
			grandTotalElement[0].lastElementChild.innerText = (parseFloat(grandTotal, 10) + parseFloat(itemPrice, 10) * flag).toFixed(2);
			// item total quantity
			Array.from(button.parentNode.children)
				.filter(elem => elem.matches(".wrapper-line__quantity"))[0]
				.innerHTML = parseInt(quantity, 10) + flag;
			button.parentNode.parentNode.remove();
		}
	});
}

// ✅ addEventListener to all cards
// increas by one 
for (const card of cards) {
	let flag = 1;

	card.addEventListener('mousedown', (e) => {
		card.classList.add('card__button_bgcolor_red');
	});

	card.addEventListener('mouseup', (e) => {
		card.classList.remove('card__button_bgcolor_red');
	});

	card.addEventListener('click', (e) => {
		e.preventDefault();

		var categoryId = Array.from(card.parentNode.parentNode.children).filter(elem => elem.matches(".card__category"))[0].defaultValue;
		var productId = Array.from(card.parentNode.parentNode.children).filter(elem => elem.matches(".card__product"))[0].defaultValue;		

			// set new value to cart's badge
			const badge = document.getElementsByClassName('webstore-badge');
			let badgeValue = badge[0].getAttribute('value');	
			let newBadgeValue = parseInt(badgeValue) + 1;		
			$('.webstore-badge').attr("value", newBadgeValue);
		
		// process click on card (adding item to cart)	
		$.post("/webstore2/cart", {
			categoryId: categoryId,
			productId: productId,
			orderedQty: 1 * flag
		}).done(function() {
			console.log("Done");
		}).fail(function() {
			console.log("Fail");
		});
	});
}
