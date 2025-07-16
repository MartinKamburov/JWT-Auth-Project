import { useEffect, useState } from "react";
import { LogoutButton } from "./LogoutButton";
import { useNavigate } from "react-router-dom";

const API_BASE = import.meta.env.VITE_API_URL ?? "http://localhost:8080";

export default function TestPage() {
  const navigate = useNavigate();
  const [message, setMessage] = useState("Loading…");

  useEffect(() => {
    const token = localStorage.getItem("jwt");
    fetch(`${API_BASE}/api/auth/test-controller`, {
      headers: { Authorization: `Bearer ${token}` },
    })
      .then(r => {
        if (!r.ok) throw new Error("Not authorized");
        return r.text();
      })
      .then(txt => setMessage(txt))
      .catch(_ => {
        // token invalid or expired → send back to login
       navigate("/login");
      });
  }, []);

  return (
    <div className="p-4">
      <h1>Test Controller</h1>
      <p>Server says: {message}</p>
      <LogoutButton/> 
    </div>
  );
}