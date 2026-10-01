(function (document, $) {
  'use strict';
    alert('Clientlib JS loaded'); // ✅ Confirms JS file is loaded


  $(document).on('dialog-ready', function () {
    const placeholderType = $('[name="./placeholder"]');
    const promoId = $('[name="./promoId"]');  
	const promoText = $('[name="./promoText"]');

    // Check if placeholderType and promoId are valid jQuery objects
    if (!placeholderType || placeholderType.length === 0 || !promoId || promoId.length === 0 || !promoText || promoText.length === 0) {
      return; // Exit if either is not found
    }

    function togglePromoIdFieldVisibility(selectedValue) {
      const promoIdField = promoId.closest('.coral-Form-fieldwrapper')[0];
      if (!promoIdField) return;

      if (['disco_promo'].includes(selectedValue)) {
        promoIdField.style.display = ''; // Show the field
      } else {
        promoIdField.style.display = 'none'; // Hide the field
      }
    }
	
	function togglePromoTextFieldVisibility(selectedValue) {
      const promoTextField = promoText.closest('.coral-Form-fieldwrapper')[0];
      if (!promoTextField) return;

      if (['disco_promo'].includes(selectedValue)) {
        promoTextField.style.display = ''; // Show the field
      } else {
        promoTextField.style.display = 'none'; // Hide the field
      }
    }

    setTimeout(function () {
      togglePromoIdFieldVisibility(placeholderType[0].value);
	  togglePromoTextFieldVisibility(placeholderType[0].value);
    }, 100);
	

    placeholderType[0]?.addEventListener('change', function(event) {
      togglePromoIdFieldVisibility(placeholderType[0].value);
	  togglePromoTextFieldVisibility(placeholderType[0].value);
    });
  });
})(document, Granite.$);