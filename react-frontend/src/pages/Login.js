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
        <div className="flex justify-center bg-stone-200 w-screen flex-grow max-w-[1240px]">
            {success ? (
                <div className="mt-12">
                    <h1>you have successfully logged in!</h1>
                </div>
            ) : (
                <div className="flex flex-col items-center pt-10 w-72 h-72 border border-stone-600 rounded-xl border-black mt-20 md:mt-44">
                    <h1>Login</h1>
                    <form onSubmit={handleSubmit} className="flex flex-col">
                        <label>Email:</label>
                        <input ref={emailRef} value={email} onChange={(event) => setEmail(event.target.value)} type="text" required />
                        <label>Password:</label>
                        <input value={password} onChange={(event) => setPassword(event.target.value)} type="password" required />
                        <p ref={errorRef} className={errorMsg ? "font-bold" : "hidden"}>{errorMsg}</p>
                        <button className="rounded border border-black mt-4 p-2">Log in</button>
                    </form>
                </div>
            )}
        </div>
    );
}

export default Login;