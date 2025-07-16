import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import AuthPage from './AuthPage';
import TestPage from "./Test";

function App() {

  return (
    <BrowserRouter>
      <Routes>
        {/* Auth form at /login */}
        <Route path="/login" element={<AuthPage />} />

        {/* Protected React route at /test */}
        <Route path="/test" element={<TestPage />} />

        {/* Any other path → redirect to /login */}
        <Route path="*" element={<Navigate to="/login" replace />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App