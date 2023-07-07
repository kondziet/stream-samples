import { createContext, useState } from "react";

const AuthenticationContext = createContext();

function AuthenticationProvider({ children }) {

    const [authentication, setAuthentication] = useState({});

    return(
        <AuthenticationContext.Provider value={{ authentication, setAuthentication }}>
            {children}
        </AuthenticationContext.Provider>
    );
}

export { AuthenticationProvider };
export default AuthenticationContext;