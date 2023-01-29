import axios from "axios";

export default class LikeService {
    addLike(body) {
        return axios.post("/likes", body)
    }

    deleteLike(id) {
        return axios.delete("/likes/" + id)
    }
}