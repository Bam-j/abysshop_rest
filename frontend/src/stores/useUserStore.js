import { create } from 'zustand';
import api from '../api/axiosInstance';

const initialState = {
  user: null,
  cartQuantity: 0,
};

const useUserStore = create((set, get) => ({
  ...initialState,

  setUser: user => {
    set({ user });
    get().updateCartQuantity(user);
  },
  setCartQuantity: quantity => set({ cartQuantity: quantity }),
  resetUser: () => set(initialState),

  updateCartQuantity: async userParam => {
    const token = localStorage.getItem('accessToken');
    const user = userParam || get().user;

    if (!token || !user || !user.cartId) {
      console.warn('updateCartQuantity 차단됨: user 또는 cartId 누락');
      return;
    }

    try {
      const res = await api.get(
        `/carts/${user.cartId}/quantity`,
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
          },
        });
      const quantity = typeof res.data === 'number' ? res.data : res.data.quantity;
      set({ cartQuantity: quantity });
    } catch (error) {
      console.error('장바구니 수량 업데이트 실패:', error);
    }
  },
}));

export default useUserStore;
