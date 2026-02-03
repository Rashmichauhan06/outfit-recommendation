package com.outfit.config;

import com.outfit.model.Product;
import com.outfit.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(ProductRepository repository) {
        return args -> {
            // If you want fresh seed every time, comment this out.
            if (repository.count() > 0) return;

            List<Product> products = List.of(

                // ---------------- ACCESSORIES (5) ----------------
                Product.builder()
                    .name("AI - Crew Length Socks")
                    .category("ACCESSORY")
                    .color("MULTI")
                    .style("CASUAL")
                    .season("ALL")
                    .occasion("DAILY")
                    .price(399.0)
                    .imageUrl("https://cdn.shopify.com/s/files/1/0676/7689/7585/files/MG_8661-1.jpg?v=1760426821&width=800")
                    .build(),

                Product.builder()
                    .name("Saucy Club Socks Set of Three")
                    .category("ACCESSORY")
                    .color("BLUE_BLACK_CREAM")
                    .style("CASUAL")
                    .season("ALL")
                    .occasion("DAILY")
                    .price(999.0)
                    .imageUrl("https://cdn.culture-circle.com/media/cc_images_url_creator/new_2025_31/travis%20pack%5B1%5D.png")
                    .build(),

                Product.builder()
                    .name("Classic Ball Cap Pink")
                    .category("ACCESSORY")
                    .color("PINK")
                    .style("CASUAL")
                    .season("SUMMER")
                    .occasion("DAILY")
                    .price(6298.0)
                    .imageUrl("https://images.pexels.com/photos/994517/pexels-photo-994517.jpeg?auto=compress&cs=tinysrgb&w=800")
                    .build(),

                Product.builder()
                    .name("Minimalist Sunglasses")
                    .category("ACCESSORY")
                    .color("BLACK")
                    .style("CASUAL")
                    .season("SUMMER")
                    .occasion("DAILY")
                    .price(2799.0)
                    .imageUrl("https://images.pexels.com/photos/701877/pexels-photo-701877.jpeg?auto=compress&cs=tinysrgb&w=800")
                    .build(),

                 // -------- ACCESSORIES --------

                 // OFFICE
               Product.builder()
                     .name("Formal Leather Belt Black")
                     .category("ACCESSORY")
                     .color("BLACK")
                     .style("FORMAL")
                     .season("ALL")
                     .occasion("OFFICE")
                     .price(1299.0)
                     .imageUrl("http://localhost:8089/images/belt.webp")
                     .build(),

                 // PARTY
              Product.builder()
                     .name("Metal Chain Necklace")
                     .category("ACCESSORY")
                     .color("SILVER")
                     .style("PARTY")
                     .season("ALL")
                     .occasion("PARTY")
                     .price(1999.0)
                     .imageUrl("http://localhost:8089/images/metal-chain.webp")
                     .build(),

                 // FESTIVE / ETHNIC
                 Product.builder()
                     .name("Ethnic Silk Stole")
                     .category("ACCESSORY")
                     .color("RED_GOLD")
                     .style("ETHNIC")
                     .season("ALL")
                     .occasion("FESTIVE")
                     .price(1499.0)
                     .imageUrl("http://localhost:8089/images/silk.jpg")
                     .build(),



                // ---------------- TOPS (5) ----------------
                Product.builder()
                    .name("Essentials Fleece Hoodie Black")
                    .category("TOP")
                    .color("BLACK")
                    .style("CASUAL")
                    .season("WINTER")
                    .occasion("DAILY")
                    .price(3591.0)
                    .imageUrl("https://cdn.culture-circle.com/media/cc_images_url_creator/25%20NOV/Untitled%20design%20-%202025-11-27T163134.391.png")
                    .build(),

                Product.builder()
                    .name("Essentials Tee Cream")
                    .category("TOP")
                    .color("CREAM")
                    .style("CASUAL")
                    .season("SUMMER")
                    .occasion("DAILY")
                    .price(3363.0)
                    .imageUrl("https://images.pexels.com/photos/10026474/pexels-photo-10026474.jpeg?auto=compress&cs=tinysrgb&w=800")
                    .build(),

                Product.builder()
                    .name("Crewneck Tee Black")
                    .category("TOP")
                    .color("BLACK")
                    .style("CASUAL")
                    .season("ALL")
                    .occasion("DAILY")
                    .price(2964.0)
                    .imageUrl("https://images.stockx.com/images/Fear-of-God-Essentials-Kids-Jersey-Crewneck-Tee-FW24-Black.jpg")
                    .build(),

                Product.builder()
                    .name("Lightweight Jacket White")
                    .category("TOP")
                    .color("WHITE")
                    .style("CASUAL")
                    .season("WINTER")
                    .occasion("DAILY")
                    .price(21498.0)
                    .imageUrl("https://images.pexels.com/photos/434418/pexels-photo-434418.jpeg?auto=compress&cs=tinysrgb&w=800")
                    .build(),

                Product.builder()
                    .name("Athleisure Zip Hoodie Grey")
                    .category("TOP")
                    .color("GREY")
                    .style("SPORT")
                    .season("WINTER")
                    .occasion("DAILY")
                    .price(4999.0)
                    .imageUrl("https://images.pexels.com/photos/6311387/pexels-photo-6311387.jpeg?auto=compress&cs=tinysrgb&w=800")
                    .build(),
                    
                 // -------- TOPS --------

                 // OFFICE
                 Product.builder()
                     .name("Formal White Cotton Shirt")
                     .category("TOP")
                     .color("WHITE")
                     .style("FORMAL")
                     .season("ALL")
                     .occasion("OFFICE")
                     .price(1999.0)
                     .imageUrl("http://localhost:8089/images/white-cotton.webp")
                     .build(),

                 // PARTY
                Product.builder()
                     .name("Black Satin Party Shirt")
                     .category("TOP")
                     .color("BLACK")
                     .style("PARTY")
                     .season("ALL")
                     .occasion("PARTY")
                     .price(2799.0)
                     .imageUrl("http://localhost:8089/images/black-satin.jpg")
                     .build(),

                 // FESTIVE / ETHNIC
               Product.builder()
                     .name("Ethnic Kurta Off White")
                     .category("TOP")
                     .color("OFF_WHITE")
                     .style("ETHNIC")
                     .season("ALL")
                     .occasion("FESTIVE")
                     .price(3499.0)
                     .imageUrl("http://localhost:8089/images/white-kurta.jpg")
                     .build(),



                // ---------------- BOTTOMS (6) ----------------
                //
               

                // ✅ Black Cargo Pants (Wikimedia direct)
                     
                  // -------- BOTTOMS --------

                  // OFFICE
                  Product.builder()
                      .name("Formal Navy Blue Trousers")
                      .category("BOTTOM")
                      .color("NAVY")
                      .style("FORMAL")
                      .season("ALL")
                      .occasion("OFFICE")
                      .price(2499.0)
                      .imageUrl("http://localhost:8089/images/navy.webp")
                      .build(),

                  // PARTY
             Product.builder()
                      .name("Slim Fit Black Party Jeans")
                      .category("BOTTOM")
                      .color("BLACK")
                      .style("PARTY")
                      .season("ALL")
                      .occasion("PARTY")
                      .price(2899.0)
                      .imageUrl("http://localhost:8089/images/black-jeans.webp")
                      .build(),

                  // FESTIVE / ETHNIC
                  Product.builder()
                      .name("Ethnic Pajama Pants White")
                      .category("BOTTOM")
                      .color("WHITE")
                      .style("ETHNIC")
                      .season("ALL")
                      .occasion("FESTIVE")
                      .price(1799.0)
                      .imageUrl("http://localhost:8089/images/pajama.webp")
                      .build(),

                Product.builder()
                    .name("Black Cargo Pants")
                    .category("BOTTOM")
                    .color("BLACK")
                    .style("CASUAL")
                    .season("ALL")
                    .occasion("DAILY")
                    .price(2499.0)
                    .imageUrl("http://localhost:8089/images/black-cargo-pants.avif")
                    .build(),

                // ✅ Brown Sweatpants (Pexels direct)
                Product.builder()
                    .name("Brown Sweatpants")
                    .category("BOTTOM")
                    .color("BROWN")
                    .style("CASUAL")
                    .season("WINTER")
                    .occasion("DAILY")
                    .price(1599.0)
                    .imageUrl("http://localhost:8089/images/brown-sweatpants.jpg")
                    .build(),

                Product.builder()
                    .name("Black Joggers")
                    .category("BOTTOM")
                    .color("BLACK")
                    .style("SPORT")
                    .season("ALL")
                    .occasion("DAILY")
                    .price(1899.0)
                    .imageUrl("http://localhost:8089/images/black-joggers.jpg")
                    .build(),

                Product.builder()
                    .name("Beige Chinos")
                    .category("BOTTOM")
                    .color("BEIGE")
                    .style("CASUAL")
                    .season("ALL")
                    .occasion("DAILY")
                    .price(2199.0)
                    .imageUrl("http://localhost:8089/images/beige-chinos.webp")
                    .build(),

                Product.builder()
                    .name("Denim Jeans Blue")
                    .category("BOTTOM")
                    .color("BLUE")
                    .style("CASUAL")
                    .season("ALL")
                    .occasion("DAILY")
                    .price(2499.0)
                    .imageUrl("http://localhost:8089/images/blue-jeans-denim.jpg")
                    .build(),


                // ---------------- FOOTWEAR (5) ----------------
                    
                 // -------- FOOTWEAR --------

                 // OFFICE
                Product.builder()
                     .name("Black Leather Formal Shoes")
                     .category("FOOTWEAR")
                     .color("BLACK")
                     .style("FORMAL")
                     .season("ALL")
                     .occasion("OFFICE")
                     .price(3999.0)
                     .imageUrl("http://localhost:8089/images/leather-black.webp")
                     .build(),

                 // PARTY
                 Product.builder()
                     .name("High Top Party Sneakers")
                     .category("FOOTWEAR")
                     .color("BLACK_WHITE")
                     .style("PARTY")
                     .season("ALL")
                     .occasion("PARTY")
                     .price(5999.0)
                     .imageUrl("http://localhost:8089/images/sneakers.avif")
                     .build(),

                 // FESTIVE / ETHNIC
                Product.builder()
                     .name("Ethnic Brown Mojari")
                     .category("FOOTWEAR")
                     .color("BROWN")
                     .style("ETHNIC")
                     .season("ALL")
                     .occasion("FESTIVE")
                     .price(3499.0)
                     .imageUrl("http://localhost:8089/images/brown-mojari.webp")
                     .build(),

                Product.builder()
                    .name("Adizero Evo SL 'Black White'")
                    .category("FOOTWEAR")
                    .color("BLACK_WHITE")
                    .style("SPORT")
                    .season("ALL")
                    .occasion("DAILY")
                    .price(13395.0)
                    .imageUrl("https://image.goat.com/transform/v1/attachments/product_template_additional_pictures/images/106/089/021/original/1494005_01.jpg.jpeg")
                    .build(),

          

                Product.builder()
                    .name("Yeezy Foam Runner Mx Cinder")
                    .category("FOOTWEAR")
                    .color("BLACK")
                    .style("SPORT")
                    .season("ALL")
                    .occasion("DAILY")
                    .price(5814.0)
                    .imageUrl("https://cdn.culture-circle.com/media/cc_images_url_creator/12%20Dec%202025/30%20dec%202025/Untitled%20design%20-%202026-01-07T033234.978.png")
                    .build(),

                Product.builder()
                    .name("White Sneakers")
                    .category("FOOTWEAR")
                    .color("WHITE")
                    .style("CASUAL")
                    .season("ALL")
                    .occasion("DAILY")
                    .price(3999.0)
                    .imageUrl("https://images.pexels.com/photos/19090/pexels-photo.jpg?auto=compress&cs=tinysrgb&w=800")
                    .build(),

                Product.builder()
                    .name("Black Slides")
                    .category("FOOTWEAR")
                    .color("BLACK")
                    .style("CASUAL")
                    .season("SUMMER")
                    .occasion("DAILY")
                    .price(1499.0)
                    .imageUrl("https://images.pexels.com/photos/292998/pexels-photo-292998.jpeg?auto=compress&cs=tinysrgb&w=800")
                    .build()
            );

            repository.saveAll(products);
            System.out.println("✅ Seeded " + products.size() + " products successfully!");
        };
    }
}
