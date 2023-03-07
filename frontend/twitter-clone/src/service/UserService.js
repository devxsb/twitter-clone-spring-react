import axios from "axios";

export default class UserService {
    getUserByUsername(username) {
        return axios.get("/users/" + username)
    }

    uploadUserProfileImage(file, username) {
        return axios.post(`/users/${username}/image/upload`, file, {
            headers: {
                "Content-Type": "multipart/form-data"
            }
        })
    }

    editProfile(body, id) {
        return axios.put(`/users/${id}`, body)
    }
}