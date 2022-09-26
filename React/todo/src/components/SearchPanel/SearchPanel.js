import React from "react";

const SearchPanel = () => {
	const searchStyle = {
		fontSize: '25px',
		backgroundColor: 'lightgray'
	};
	const placeholderText = "Place search here...";
	return <input style={searchStyle}
		type='text' placeholder={placeholderText} />
};

export default SearchPanel;