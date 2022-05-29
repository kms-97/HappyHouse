const request = async (url, options) => {
    try {
        const response = await fetch(url, options);
        if (response.ok) {
            return response;
        } else {
            const errorData = {
                status: response.status,
                message: response.statusText
            };
            throw errorData;
        }
    } catch (e) {
        throw {
            status: e.status,
            message: e.message
        };
    }
};

export const fetchData = async (url, options = {}) => {
    const URL = `${process.env.VUE_APP_BASE_URL}${url}`;

    try {
        const response = await request(URL, options);
        return {
            isError: false,
            data: await response.json(),
        };
    } catch (e) {
        return {
            isError: true,
            data: e
        };
    }
};

export const getToken = async (url, options = {}) => {
    const URL = `${process.env.VUE_APP_BASE_URL}${url}`;

    try {
        const response = await fetch(URL, options);
        if (response.ok) {
            const data = await response.text();
            return data;
        } else {
            const errorData = {
                status: response.status,
                message: response.statusText
            };
            throw errorData;
        }
    } catch (e) {
        throw {
            status: e.status,
            message: e.message
        };
    }
};

