import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import clientRequest from "../api/clientRequest";

function Register() {

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [repeatedPassword, setRepeatedPassword] = useState("");
    const [nickname, setNickname] = useState("");
    const [errorMsg, setErrorMsg] = useState("");

    const navigate = useNavigate();

    useEffect(() => {
        setErrorMsg("");
    }, [email, password, nickname]);

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            const response = await clientRequest.post(
                "/api/authentication/register",
                {email, password, nickname}
            );

            setEmail("");
            setPassword("");
            setRepeatedPassword("");

            navigate("/login");
        } catch (error) {
            if (!error?.response) {
                setErrorMsg("No server response");
            } else if (error.response?.status === 400) {
                setErrorMsg("Missing email or password")
            } else if (error.response?.status === 403) {
                setErrorMsg("Invalid e-mail or password")
            }
        }

    };

    return(
        <div className="w-screen flex-grow max-w-[1240px] flex justify-center">
            <div>
                <form onSubmit={handleSubmit} className="p-4 m-2">
                    <label>Email:</label>
                    <input value={email} onChange={(event) => setEmail(event.target.value)} className="border border-black rounded" type="text" required />
                    <label>Password:</label>
                    <input value={password} onChange={(event) => setPassword(event.target.value)} className="border border-black rounded" type="password" required />
                    <label>Confirm password:</label>
                    <input value={repeatedPassword} onChange={(event) => setRepeatedPassword(event.target.value)} className="border border-black rounded" type="password" required />
                    <label>Nickname:</label>
                    <input value={nickname} onChange={(event) => setNickname(event.target.value)} className="border border-black rounded" type="text" required />
                    <button className="border border-black rounded">Register</button>
                    <p className={errorMsg ? "font-bold" : "hidden"}>{errorMsg}</p>
                </form>
            </div>
        </div>
    );
}

export default Register;