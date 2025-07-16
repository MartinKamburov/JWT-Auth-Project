import { useNavigate } from "react-router-dom";
import { Button } from "react-bootstrap";

export function LogoutButton() {
  const navigate = useNavigate();

  const handleLogout = () => {
    // 1) Remove the JWT so future requests won’t be authenticated
    localStorage.removeItem("jwt");

    // 2) Optionally, you can clear *all* localStorage, or any other app state
    // localStorage.clear();

    // 3) Redirect the user back to the login page
    navigate("/login");
  };

  return (
    <Button variant="outline-secondary" onClick={handleLogout}>
      Log out
    </Button>
  );
}