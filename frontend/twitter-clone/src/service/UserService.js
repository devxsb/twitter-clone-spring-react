import axios from "axios";

export default class UserService {
    getUser(id) {
        return axios.get("/users/" + id)
    }
}