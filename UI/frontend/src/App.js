import logo from './logo.svg';
import './App.css';
import {useState} from "react";

function App() {
    const [text, setText] = useState("Hello from React")
    const [json, setJson] = useState("Hello from React")

    const onFirstLiClickHandler = () => {
        fetchText()
    }

    const onSecondsLiClickHandler = () => {
        fetchJson()
    }

    let fetchText = async () => {
        const response = await fetch('/api/demo');
        await response.text().then(data => setText(data)).catch(reason => setText("Error"))
    }

    let fetchJson = async () => {
        const response = await fetch('/api/json/hello');
        await response.text().then(data => setJson(data)).catch(reason => setJson("Error"))
    }

    return (
        <div className="App">
            <header>
                <img src={logo} className="App-logo" alt="logo"/>
            </header>
            <ul>
                <li onClick={onFirstLiClickHandler}>
                    {text}
                </li>
                <li onClick={onSecondsLiClickHandler}>
                    {json}
                </li>
            </ul>

        </div>
    );
}

export default App;
