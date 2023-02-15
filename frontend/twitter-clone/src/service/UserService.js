import axios from "axios";

export default class UserService {
    getUserByUsername(username) {
        return axios.get("/users/" + username)
    }
}