import TodoList from '../TodoList';
import AppHeader from '../AppHeader';
import SearchPanel from '../SearchPanel';
import { useState, useEffect } from 'react';

const App = () => {
	const [counter, setCounter] = useState(0);

	  const updateCounter = () => {
   return setCounter(previousState => {
      return  previousState + 1 }
    );
  }

	const onDeleted = (id) => { console.log(id)};

	const todoData = [
		{ label: "Drink Coffee", important: false , id: 1},
		{ label: "Make React App", important: true, id: 2 },
		{ label: "Drink Tea", important: false, id: 3}
	];
	const isLoggedIn = true;
	const loginBox = <span>Login</span>;

	return (<div>
		<p>{(new Date().toString())}</p>
		<p>{ counter}</p>
		{ isLoggedIn ? null : loginBox }
		<AppHeader />
		<SearchPanel />
		<TodoList todos={todoData} onDeleted={onDeleted} updateCounter={ updateCounter} />
	</div>
	);
}

export default App;