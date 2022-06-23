package io.github.kabirnayeem99.minigallery.data.dataSource.development

import io.github.kabirnayeem99.minigallery.BuildConfig
import io.github.kabirnayeem99.minigallery.domain.entity.ImageFolder
import io.github.serpro69.kfaker.faker
import kotlinx.coroutines.delay

/**
 * Random Data Provider object to provide demo data before implementing real API
 * Only will work on debug mode.
 */
object RandomDataProvider {

    private val faker = faker { }

    /**
     * Returns a list of random image folders if the app is in debug mode, otherwise it returns an
     * empty list
     *
     * @return A list of ImageFolder objects
     */
    suspend fun provideRandomImageFolders(): List<ImageFolder> {

        return if (BuildConfig.DEBUG) {
            delay(faker.random.nextLong(1000))
            listOf(
                ImageFolder(
                    "",
                    faker.artist.names(),
                    faker.random.nextInt(12),
                    getRandomImageUrl(),
                ),
                ImageFolder(
                    "",
                    faker.artist.names(),
                    faker.random.nextInt(12),
                    getRandomImageUrl(),
                ),
                ImageFolder(
                    "",
                    faker.artist.names(),
                    faker.random.nextInt(12),
                    getRandomImageUrl(),
                ),
                ImageFolder(
                    "",
                    faker.artist.names(),
                    faker.random.nextInt(12),
                    getRandomImageUrl(),
                ),
                ImageFolder(
                    "",
                    faker.artist.names(),
                    faker.random.nextInt(12),
                    getRandomImageUrl(),
                ),
                ImageFolder(
                    "",
                    faker.artist.names(),
                    faker.random.nextInt(10),
                    getRandomImageUrl(),
                ),
                ImageFolder(
                    "",
                    faker.artist.names(),
                    faker.random.nextInt(10),
                    getRandomImageUrl(),
                ),
                ImageFolder(
                    "",
                    faker.artist.names(),
                    faker.random.nextInt(10),
                    getRandomImageUrl(),
                ),
                ImageFolder(
                    "",
                    faker.artist.names(),
                    faker.random.nextInt(10),
                    getRandomImageUrl(),
                ),
                ImageFolder(
                    "",
                    faker.artist.names(),
                    faker.random.nextInt(10),
                    getRandomImageUrl(),
                ),
                ImageFolder(
                    "",
                    faker.artist.names(),
                    faker.random.nextInt(10),
                    getRandomImageUrl(),
                ),
                ImageFolder(
                    "",
                    faker.artist.names(),
                    faker.random.nextInt(10),
                    getRandomImageUrl(),
                ),
                ImageFolder(
                    "",
                    faker.artist.names(),
                    faker.random.nextInt(10),
                    getRandomImageUrl(),
                ),
                ImageFolder(
                    "",
                    faker.artist.names(),
                    faker.random.nextInt(10),
                    getRandomImageUrl(),
                ),

                )
        } else emptyList()
    }

    /**
     * Returns a random image url.
     *
     * @return A string
     */
    private fun getRandomImageUrl(): String {
        return "https://wallpaperping.com/wp-content/uploads/2018/05/wc1744416-1129x768.jpg"
    }

}