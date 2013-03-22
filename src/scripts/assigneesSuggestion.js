
/**
 * Provides suggestions for state names (USA).
 * @class
 * @scope public
 */
function AssigneeSuggestions(id_tugas) {	
	this.fieldKey = "username";
	this.displayKey = "display";
	this.id_tugas = id_tugas;
}

/**
 * Request suggestions for the given autosuggest control. 
 * @scope protected
 * @param oAutoSuggestControl The autosuggest control to provide suggestions for.
 */
AssigneeSuggestions.prototype.requestSuggestions = function (oAutoSuggestControl /*:AutoSuggestControl*/,
                                                          bTypeAhead /*:boolean*/) {
    var aSuggestions = [];
    var sTextboxValue = oAutoSuggestControl.textbox.value;
    
	if (sTextboxValue.length > 0) {
		ajax_get("./ajax/updateTugas.php?suggesta&id_tugas=" + this.id_tugas + "&start=" + sTextboxValue, function (xhr) {
			var json_obj = JSON.parse(xhr.responseText);
			if (json_obj.responseStatus == 200) {
				aSuggestions = json_obj.suggestedAssignees;
				
				//provide suggestions to the control
				oAutoSuggestControl.autosuggest(aSuggestions, false);
			}
		});
	}
};
