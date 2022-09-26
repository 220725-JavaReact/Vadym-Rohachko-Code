import React from "react";
import { useEffect } from 'react';

// const TodoItem = (props) => {
// 	return <span>{ props.label }</span>
// };

const TodoItem = ({ label, important = false, id, onDeleted, updateCounter }) => {
	const spanStyle = {
		color: important ? 'tomato' : 'black'
	}


	return <span style={spanStyle}>{label}
		<input type='button' value='Count' onClick={() => updateCounter()} />
		<input type='button' value='Delete' onClick={() => onDeleted(id)} /></span>
};

export default TodoItem;