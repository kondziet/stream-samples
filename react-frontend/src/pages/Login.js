import { useEffect, useRef, useState } from "react";
import useAuthenticationContext from "../hooks/useAuthentication";
import clientRequest from "../api/clientRequest";

function Login() {

    const emailRef = useRef();
    const errorRef = useRef();

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [errorMsg, setErrorMsg] = useState("");
    const [success, setSuccess] = useState(false);

    const { setAuthentication } = useAuthenticationContext();

    useEffect(() => {
        emailRef.current.focus();
    }, []);

    useEffect(() => {
        setErrorMsg("");
    }, [email, password]);

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            const response = await clientRequest.post(
                "/api/authentication/authenticate",
                {email, password}
            );
            const accessToken = response.data.token;
            setAuthentication({ accessToken });

            setEmail("");
            setPassword("");
            setSuccess(true);
        } catch (error) {
            if (!error?.response) {
                setErrorMsg("No server response");
            } else if (error.response?.status === 400) {
                setErrorMsg("Missing email or password")
            } else if (error.response?.status === 403) {
                setErrorMsg("Invalid e-mail or password")
            }
            errorRef.current.focus();
        }

    };

    return(
        <div className="flex justify-center items-center bg-green-500/50">
            {success ? (
                <div>
                    <h1>you have successfully logged in!</h1>
                </div>
            ) : (
                <div>
                    <h1>Login</h1>
                    <form onSubmit={handleSubmit} className="flex flex-col">
                        <label>email:</label>
                        <input ref={emailRef} value={email} onChange={(event) => setEmail(event.target.value)} type="text" required />
                        <label>Password:</label>
                        <input value={password} onChange={(event) => setPassword(event.target.value)} type="password" required />
                        <p ref={errorRef} className={errorMsg ? "font-bold" : "hidden"}>{errorMsg}</p>
                        <button>Log in</button>
                    </form>
                </div>
            )}
        </div>
    );
}

export default Login;