import React, {useState} from 'react';
import TwitterIcon from '@mui/icons-material/Twitter';
import SearchIcon from '@mui/icons-material/Search';
import PeopleIcon from '@mui/icons-material/People';
import ChatIcon from '@mui/icons-material/Chat';
import bg from '../images/bird.svg'
import AuthService from "../service/AuthService";
import {useDispatch} from "react-redux";
import {login} from "../redux/reduxSlice";
import {useNavigate} from "react-router-dom";

const Home = () => {
    const [username, setUsername] = useState(null)
    const [password, setPassword] = useState(null)

    const dispatch = useDispatch()
    const navigate = useNavigate()
    const loginClick = (e) => {
        let body = {
            username: username,
            password: password
        }
        let authService = new AuthService()
        authService.login(body).then(res => {
            dispatch(login(res.data))
        })
        e.preventDefault()
    }

    return (
        <div className="flex flex-col w-full h-screen m-0" style={{fontFamily: `Segoe UI, Arial, sans-serif`}}>
            <div className="flex flex-row h-screen">
                <div className="flex w-1/2"
                     style={{
                         backgroundImage: `url(${bg}`,
                         backgroundColor: " #1da1f2",
                         backgroundRepeat: "no-repeat",
                         backgroundSize: "cover"
                     }}>
                    <div className="flex flex-col justify-center w-full text-white">
                        <div className="flex flex-row items-center max-w-md w-full ml-auto mr-auto mb-12">
                            <SearchIcon fontSize="medium" style={{marginRight: "5px"}}/>
                            <span className="!font-bold text-lg">Follow your interests.</span>
                        </div>
                        <div className="flex flex-row items-center max-w-md w-full ml-auto mr-auto mb-12">
                            <PeopleIcon fontSize="medium" style={{marginRight: "5px"}}/>
                            <span className="!font-bold text-lg"
                            >Hear what your people are talking about.</span>
                        </div>
                        <div className="flex flex-row items-center max-w-md w-full ml-auto mr-auto mb-12">
                            <ChatIcon fontSize="medium" style={{marginRight: "5px"}}/>
                            <span className="!font-bold text-lg">Join the conversation.</span>
                        </div>
                    </div>
                </div>
                <div className="flex flex-col space-x-reverse w-1/2">
                    <form className="flex justify-center w-full mt-5">
                        <div className="mr-2.5">
                            <input type="text"
                                   placeholder="Username"
                                   className="border-b p-3 block placeholder-gray-dark bg-transparent focus:outline-none w-full text-sm focus-within:ring-1 focus-within:ring-primary-base"
                                   onChange={e => setUsername(e.target.value)}/>
                        </div>
                        <div className="mr-2.5">
                            <input type="password"
                                   placeholder="Password"
                                   className="border-b p-3 block placeholder-gray-dark bg-transparent focus:outline-none w-full text-sm focus-within:ring-1 focus-within:ring-primary-base"
                                   onChange={e => setPassword(e.target.value)}/>
                            <a className="text-gray-400 text-xs ml-3">Don't have an account? Sign up</a>
                        </div>
                        <button
                            className="bg-transparent py-2 px-4 rounded-2xl p-3 text-blue-400 text-sm box-border w-auto h-11 font-bold"
                            style={{color: "#1DA1F2", border: `1px solid #1DA1F2`}} type="submit"
                            onClick={loginClick}>Log in
                        </button>
                    </form>
                    <div className="max-h-72 m-auto max-w-sm">
                        <TwitterIcon style={{color: "#1DA1F2", marginBottom: "10px"}} fontSize="large"/>
                        <h1 className="text-2xl mb-6 font-bold" style={{lineHeight: "32px"}}>See what's happening in
                            <br/> the world right now</h1>
                        <span>Join Twitter today.</span>
                        <button
                            className="flex justify-center items-center w-full h-11 text-white font-bold py-2 px-4 rounded-2xl mt-3"
                            style={{backgroundColor: "#1DA1F2", border: `1px solid #1DA1F2`}}
                            onClick={() => navigate("/signup")}>Sign up
                        </button>
                        <button
                            className="flex justify-center items-center w-full h-11 bg-transparent py-2 px-4 rounded-2xl mt-1"
                            style={{color: "#1DA1F2", border: `1px solid #1DA1F2`}}
                            onClick={() => navigate("/login")}> Log in
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Home;