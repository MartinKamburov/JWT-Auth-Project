import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import {
  Container,
  Row,
  Col,
  Card,
  Form,
  Button,
  Alert,
  Spinner,
} from "react-bootstrap";

const API_BASE = import.meta.env.VITE_API_URL ?? "http://localhost:8080";
type Mode = "login" | "register";

export default function AuthPage() {
  const navigate = useNavigate();
  const [mode, setMode] = useState<Mode>("login");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName]   = useState("");
  const [email, setEmail]         = useState("");
  const [password, setPassword]   = useState("");
  const [loading, setLoading]     = useState(false);
  const [error, setError]         = useState<string | null>(null);

  const toggleMode = () => {
    setMode(mode === "login" ? "register" : "login");
    setError(null);
  };

  const handleSubmit: React.FormEventHandler<HTMLFormElement> = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError(null);

    try {
      const endpoint = mode === "login" ? "authenticate" : "register";
      const payload = mode === "login"
        ? { email, password }
        : { firstName, lastName, email, password };

      const res = await fetch(`${API_BASE}/api/v1/auth/${endpoint}`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        credentials: "include",
        body: JSON.stringify(payload),
      });

      if (!res.ok) throw new Error(await res.text());

      console.log("Here is the response: ", res);

      // both register and login now return { token }
      const { token } = await res.json();
      console.log("Here is the token", token);

      // localStorage is part of the browser's Web Storage API - a simple Key/Value store that lives in the user's broswer and persists even after they close the tab or restart the browser
      // it lives on the end‐user’s machine, scoped by your app’s origin (protocol + domain + port).
      // When we do this code below we are simply saving that JWT under the "jwt" key in the browser’s persistent storage area so you can read it on subsequent page loads or API calls
      localStorage.setItem("jwt", token);

      // 6) once it succeeds, route into your React “Test” page
      navigate("/test");
    } catch (err: any) {
      setError(err.message || "Check your credentials");
    } finally {
      setLoading(false);
    }
  };

  return (
    <Container fluid className="vh-100 d-flex align-items-center justify-content-center bg-light p-3">
      <Row className="w-100">
        <Col xs={12} sm={10} md={6} lg={4} className="mx-auto">
          <Card className="shadow">
            <Card.Body>
              <h2 className="text-center mb-4">
                {mode === "login" ? "Log in" : "Sign up"}
              </h2>

              {error && <Alert variant="danger">{error}</Alert>}

              <Form onSubmit={handleSubmit} className="mb-3">
                {mode === "register" && (
                  <>
                    <Form.Group className="mb-3" controlId="firstName">
                      <Form.Label>First Name</Form.Label>
                      <Form.Control
                        type="text"
                        placeholder="Your first name"
                        required
                        value={firstName}
                        onChange={(e) => setFirstName(e.target.value)}
                      />
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="lastName">
                      <Form.Label>Last Name</Form.Label>
                      <Form.Control
                        type="text"
                        placeholder="Your last name"
                        required
                        value={lastName}
                        onChange={(e) => setLastName(e.target.value)}
                      />
                    </Form.Group>
                  </>
                )}

                <Form.Group className="mb-3" controlId="email">
                  <Form.Label>Email</Form.Label>
                  <Form.Control
                    type="email"
                    placeholder="you@example.com"
                    required
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                  />
                </Form.Group>

                <Form.Group className="mb-4" controlId="password">
                  <Form.Label>Password</Form.Label>
                  <Form.Control
                    type="password"
                    placeholder="••••••••"
                    required
                    minLength={6}
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                  />
                </Form.Group>

                <Button
                  variant="primary"
                  type="submit"
                  disabled={loading}
                  className="w-100 d-flex align-items-center justify-content-center"
                >
                  {loading && <Spinner animation="border" size="sm" className="me-2" />}
                  {mode === "login" ? "Log in" : "Create account"}
                </Button>
              </Form>

              <div className="text-center">
                {mode === "login"
                  ? "Don't have an account?"
                  : "Already registered?"}{" "}
                <Button variant="link" onClick={toggleMode} className="p-0 align-baseline">
                  {mode === "login" ? "Sign up" : "Log in"}
                </Button>
              </div>
            </Card.Body>
          </Card>
        </Col>
      </Row>
    </Container>
  );
}