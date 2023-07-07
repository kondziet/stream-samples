import { useEffect } from "react";
import useAuthenticationContext from "./useAuthentication";
import { privateClientRequest } from "../api/clientRequest";

function usePrivateClientRequest() {
    const { authentication } = useAuthenticationContext();
    const token = authentication.accessToken;

    useEffect(() => {
        const requestIntercept = privateClientRequest.interceptors.request.use(config => {
            if (token) {
                config.headers.Authorization = `Bearer ${token}`;
            }
          
            return config;
        });

        const cleanup = () => {
            privateClientRequest.interceptors.request.eject(requestIntercept);
        };

        return cleanup;

    }, []);

    return privateClientRequest;
}

export default usePrivateClientRequest;