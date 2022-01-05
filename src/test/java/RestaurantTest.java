import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.time.LocalTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class RestaurantTest {
    Restaurant restaurant;

    @BeforeEach
    public void beforeEachTest() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        LocalTime openingTime = LocalTime.now().minusHours(1);
        LocalTime closingTime = LocalTime.now().plusHours(1);
        restaurant = new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        Boolean restaurantStatus = restaurant.isRestaurantOpen();
        assertTrue(restaurantStatus);
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        Boolean restaurantStatus = restaurant.isRestaurantOpen();
        assertFalse(restaurantStatus);
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



    //>>>>>>>>>>>>>>>>>>>>>>>>>>>Order Total<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void get_order_total_should_return_zero_if_nothing_selected(){
        int order_total = restaurant.getOrderTotal(null);
        assertEquals(order_total,0);
    }

    @Test
    public void get_order_total_should_return_zero_if_order_item_list_empty(){
        int order_total = restaurant.getOrderTotal(Collections.<String>emptyList());
        assertEquals(order_total,0);
    }

    @Test
    public void get_order_total_should_return_total_if_order_items_are_selected(){
        List<String> order_list = new ArrayList<String>();
        order_list.add("Sweet corn soup");
        order_list.add("Vegetable lasagne");
        int order_total = restaurant.getOrderTotal(order_list);
        assertEquals(order_total,388);
    }
    //<<<<<<<<<<<<<<<<<<<<<<<Order Total>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}