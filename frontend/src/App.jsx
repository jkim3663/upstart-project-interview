import './App.css';
import { BrowserRouter as Router, Routes, Route, BrowserRouter } from 'react-router-dom';
import LoginSignUp from './components/login/LoginSignUp';
import Dashboard from './components/dashboard/Dashboard'; 

function App() {

  return (
    <BrowserRouter>
      <div className='App'>
        <Routes>
          <Route path="/" element={<LoginSignUp />} />
          <Route path="/dashboard" element={<Dashboard />} />
        </Routes>
      </div>
    </BrowserRouter>
  )
}

export default App
