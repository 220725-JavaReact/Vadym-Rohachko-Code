import React from "react";
import TodoItem from "../TodoItem";
import { useEffect } from 'react';

const TodoList = ({ todos, onDeleted, updateCounter }) => {
  
  // // runs evry render
  // useEffect(() => {
	// 	console.log("use effect ran");
  // });
  
  // // runs only the first render
  //   useEffect(() => {
	// 	console.log("use effect ran");
  //   }, []);

  
  const elems = todos.map((elem) => {
    return (
      <li key={elem.id}>
				{/* <TodoItem label={elem.label} important={elem.important} /> */}
        <TodoItem {...elem} onDeleted={onDeleted} updateCounter={ updateCounter} />
      </li>
    );
  });

  return <ul>{elems}</ul>;
};

export default TodoList;
