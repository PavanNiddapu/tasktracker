import logo from './logo.svg';
import './App.css';
import {useState, useEffect} from "react";

function App() {
    const [text, setText] = useState("Hello from React")
    const [json, setJson] = useState("Hello from React")
    const [tasks, setTasks] = useState([])
    const [newTask, setNewTask] = useState({ name: '', description: '' })
    const [error, setError] = useState('')
    const [loading, setLoading] = useState(false)

    useEffect(() => {
        fetchTasks()
    }, [])

    const onFirstLiClickHandler = () => {
        fetchText()
    }

    const onSecondsLiClickHandler = () => {
        fetchJson()
    }

    let fetchText = async () => {
        try {
            const response = await fetch('/api/demo');
            const data = await response.text()
            setText(data)
        } catch (err) {
            setText("Error connecting to backend")
        }
    }

    let fetchJson = async () => {
        try {
            const response = await fetch('/api/json/hello');
            const data = await response.text()
            setJson(data)
        } catch (err) {
            setJson("Error connecting to backend")
        }
    }

    const fetchTasks = async () => {
        try {
            setLoading(true)
            const response = await fetch('/api/task')
            if (response.ok) {
                const data = await response.json()
                setTasks(data)
                setError('')
            } else {
                setError('Failed to fetch tasks')
            }
        } catch (err) {
            setError('Error connecting to backend')
        } finally {
            setLoading(false)
        }
    }

    const createTask = async () => {
        if (!newTask.name.trim()) {
            setError('Task name is required')
            return
        }
        
        try {
            setLoading(true)
            const response = await fetch('/api/task', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    id: Date.now(), // Simple ID generation
                    name: newTask.name,
                    description: newTask.description
                })
            })
            
            if (response.ok) {
                setNewTask({ name: '', description: '' })
                fetchTasks() // Refresh the task list
                setError('')
            } else {
                setError('Failed to create task')
            }
        } catch (err) {
            setError('Error creating task')
        } finally {
            setLoading(false)
        }
    }

    const startTask = async (taskId) => {
        try {
            const response = await fetch(`/api/task/start/${taskId}`)
            if (response.ok) {
                const result = await response.json()
                if (result.message === 'success') {
                    fetchTasks() // Refresh to see updated task
                    setError('')
                } else {
                    setError('Failed to start task')
                }
            } else {
                setError('Failed to start task')
            }
        } catch (err) {
            setError('Error starting task')
        }
    }

    const stopTask = async (taskId) => {
        try {
            const response = await fetch(`/api/task/stop/${taskId}`)
            if (response.ok) {
                const result = await response.json()
                if (result.message === 'success') {
                    fetchTasks() // Refresh to see updated task
                    setError('')
                } else {
                    setError('Failed to stop task')
                }
            } else {
                setError('Failed to stop task')
            }
        } catch (err) {
            setError('Error stopping task')
        }
    }

    return (
        <div className="App">
            <header className="App-header">
                <img src={logo} className="App-logo" alt="logo"/>
                <h1>Task Tracker</h1>
            </header>

            {error && <div className="error-message">{error}</div>}

            <div className="task-form">
                <h2>Create New Task</h2>
                <input
                    type="text"
                    placeholder="Task Name"
                    value={newTask.name}
                    onChange={(e) => setNewTask({...newTask, name: e.target.value})}
                />
                <textarea
                    placeholder="Task Description"
                    rows="3"
                    value={newTask.description}
                    onChange={(e) => setNewTask({...newTask, description: e.target.value})}
                />
                <button onClick={createTask} disabled={loading}>
                    {loading ? 'Creating...' : 'Create Task'}
                </button>
            </div>

            <div className="task-list">
                <h2>Task List {loading && '(Loading...)'}</h2>
                {tasks.length === 0 && !loading ? (
                    <p>No tasks yet. Create your first task above!</p>
                ) : (
                    tasks.map((task) => (
                        <div key={task.id} className="task-item">
                            <h3>{task.name}</h3>
                            <p>{task.description}</p>
                            <p><strong>Created:</strong> {new Date(task.createdDate).toLocaleString()}</p>
                            <p><strong>Timeline entries:</strong> {task.timelineSize || 0}</p>
                            <p><strong>Total hours worked:</strong> {task.calculateTotalHoursWorked || 0}</p>
                            <div className="task-controls">
                                <button 
                                    className="start-button"
                                    onClick={() => startTask(task.id)}
                                >
                                    Start
                                </button>
                                <button 
                                    className="stop-button"
                                    onClick={() => stopTask(task.id)}
                                >
                                    Stop
                                </button>
                            </div>
                        </div>
                    ))
                )}
            </div>

            <h2>API Test</h2>
            <ul className="demo-list">
                <li onClick={onFirstLiClickHandler}>
                    Test Demo API: {text}
                </li>
                <li onClick={onSecondsLiClickHandler}>
                    Test JSON API: {json}
                </li>
            </ul>
        </div>
    );
}

export default App;
