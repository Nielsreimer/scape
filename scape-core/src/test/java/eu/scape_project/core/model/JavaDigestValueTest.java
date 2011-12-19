package eu.scape_project.core.model;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBException;

import org.apache.commons.codec.digest.DigestUtils;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.junit.BeforeClass;
import org.junit.Test;

import eu.scape_project.core.AllCoreTest;
import eu.scape_project.core.api.DigestValue.DigestAlgorithm;

/**
 * @author <a href="mailto:carl.wilson.bl@gmail.com">Carl Wilson</a> <a
 *         href="http://sourceforge.net/users/carlwilson-bl"
 *         >carlwilson-bl@SourceForge</a> <a
 *         href="https://github.com/carlwilson-bl">carlwilson-bl@github</a> Test
 *         class for the digest value class, using apache.commons.codec to test
 *         Java implementation.
 */
public class JavaDigestValueTest {
	private static final List<File> TEST_FILES = AllCoreTest
			.getFilesFromResourceDir(AllCoreTest.TEST_DATA_ROOT);

	/**
	 * Setup method to ensure that we have test data before the class
	 */
	@BeforeClass
	public void setup() {
		assertTrue("No test data found.", TEST_FILES.size() > 0);
	}

	/**
	 * Test method for
	 * {@link eu.scape_project.core.model.JavaDigestValue#hashCode()}.
	 * 
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws FileNotFoundException
	 * @throws NoSuchAlgorithmException
	 */
	@Test
	public void testHashCode() throws FileNotFoundException,
			URISyntaxException, IOException, NoSuchAlgorithmException {
		for (File file : TEST_FILES) {
			JavaDigestValue apacheValue = JavaDigestValue.getInstance(
					DigestAlgorithm.MD5,
					DigestUtils.md5(new FileInputStream(file)));
			JavaDigestValue testValue = JavaDigestValue.getInstance(
					DigestAlgorithm.MD5, file);
			JavaDigestValue testSha256Value = JavaDigestValue.getInstance(
					DigestAlgorithm.SHA256, file);
			assertEquals(
					"apacheValue.hash() and testValue.hash() should be equal",
					apacheValue.hashCode(), testValue.hashCode());
			assertTrue(
					"apacheValue.hash() and testSha256Value.has() shouldn't be equal",
					apacheValue.hashCode() != testSha256Value.hashCode());
		}
	}

	/**
	 * Test method for
	 * {@link eu.scape_project.core.model.JavaDigestValue#getAlgorithmId()}.
	 * 
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws FileNotFoundException
	 * @throws NoSuchAlgorithmException
	 */
	@Test
	public void testGetAlgorithmId() throws FileNotFoundException,
			URISyntaxException, IOException, NoSuchAlgorithmException {
		for (File file : TEST_FILES) {
			JavaDigestValue apacheValue = JavaDigestValue.getInstance(
					DigestAlgorithm.SHA1,
					DigestUtils.sha(new FileInputStream(file)));
			JavaDigestValue testValue = JavaDigestValue.getInstance(
					DigestAlgorithm.SHA1, file);
			JavaDigestValue testSha256Value = JavaDigestValue.getInstance(
					DigestAlgorithm.SHA256, file);
			assertEquals("apacheValue.id() and testValue.id() should be equal",
					apacheValue.getAlgorithmId(), testValue.getAlgorithmId());
			assertFalse(
					"apacheValue.id() and testSha256Value.id() shouldn't be equal",
					apacheValue.getAlgorithmId().equals(
							testSha256Value.getAlgorithmId()));
		}
	}

	/**
	 * Test method for
	 * {@link eu.scape_project.core.model.JavaDigestValue#getHexDigest()}.
	 * 
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws FileNotFoundException
	 * @throws NoSuchAlgorithmException
	 */
	@Test
	public void testGetHexDigest() throws FileNotFoundException,
			URISyntaxException, IOException, NoSuchAlgorithmException {
		for (File file : TEST_FILES) {
			JavaDigestValue apacheValue = JavaDigestValue.getInstance(
					DigestAlgorithm.MD5,
					DigestUtils.md5(new FileInputStream(file)));
			JavaDigestValue testValue = JavaDigestValue.getInstance(
					DigestAlgorithm.MD5, file);
			JavaDigestValue testSha1Value = JavaDigestValue.getInstance(
					DigestAlgorithm.SHA1, file);
			assertEquals(
					"apacheValue.hexDigest() and testValue.hexDigest() should be equal",
					apacheValue.getHexDigest(), testValue.getHexDigest());
			assertFalse(
					"apacheValue.hexDigest() and testSha1Value.hexDigest() shouldn't be equal",
					apacheValue.getHexDigest().equals(
							testSha1Value.getHexDigest()));
		}
	}

	/**
	 * Test method for
	 * {@link eu.scape_project.core.model.JavaDigestValue#getDigest()}.
	 * 
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws FileNotFoundException
	 * @throws NoSuchAlgorithmException
	 */
	@Test
	public void testGetDigest() throws FileNotFoundException,
			URISyntaxException, IOException, NoSuchAlgorithmException {
		for (File file : TEST_FILES) {
			JavaDigestValue apacheValue = JavaDigestValue.getInstance(
					DigestAlgorithm.SHA1,
					DigestUtils.sha(new FileInputStream(file)));
			JavaDigestValue testValue = JavaDigestValue.getInstance(
					DigestAlgorithm.SHA1, file);
			JavaDigestValue testSha256Value = JavaDigestValue.getInstance(
					DigestAlgorithm.SHA256, file);
			assertArrayEquals(
					"apacheValue.digest() and testValue.digest() should be equal",
					apacheValue.getDigest(), testValue.getDigest());
			assertThat(
					"apacheValue.digest() and testSha256Value.digest() shouldn't be equal",
					apacheValue.getDigest(),
					IsNot.not(IsEqual.equalTo(testSha256Value.getDigest())));
		}
	}

	/**
	 * Test method for
	 * {@link eu.scape_project.core.model.JavaDigestValue#toXml()}, and
	 * {@link eu.scape_project.core.model.JavaDigestValue#getInstance(String)}.
	 * 
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws FileNotFoundException
	 * @throws JAXBException
	 * @throws NoSuchAlgorithmException
	 */
	@Test
	public void testXmlSerialization() throws FileNotFoundException,
			URISyntaxException, IOException, JAXBException,
			NoSuchAlgorithmException {
		for (File file : TEST_FILES) {
			JavaDigestValue apache256Value = JavaDigestValue.getInstance(
					DigestAlgorithm.SHA256,
					DigestUtils.sha256(new FileInputStream(file)));
			JavaDigestValue testValue = JavaDigestValue.getInstance(
					DigestAlgorithm.MD5, file);
			JavaDigestValue xmlTestValue = JavaDigestValue
					.getInstance(testValue.toXml());
			assertEquals(
					"testValue.hash() and xmlTestValue.hash() should be equal",
					testValue.hashCode(), xmlTestValue.hashCode());
			assertFalse(
					"apache256Value.hash() and xmlTestValue.hash() shouldn't be equal",
					apache256Value.hashCode() == xmlTestValue.hashCode());
		}
	}

	/**
	 * Test method for
	 * {@link eu.scape_project.core.model.JavaDigestValue#equals(Object)}.
	 * 
	 * @throws JAXBException
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws FileNotFoundException
	 * @throws NoSuchAlgorithmException
	 */
	@Test
	public void testEqualsObject() throws FileNotFoundException,
			URISyntaxException, IOException, JAXBException,
			NoSuchAlgorithmException {
		for (File file : TEST_FILES) {
			JavaDigestValue apache256Value = JavaDigestValue.getInstance(
					DigestAlgorithm.SHA256,
					DigestUtils.sha256(new FileInputStream(file)));
			JavaDigestValue testValue = JavaDigestValue.getInstance(
					DigestAlgorithm.MD5, file);
			JavaDigestValue xmlTestValue = JavaDigestValue
					.getInstance(testValue.toXml());
			assertTrue("testValue and xmlTestValue should be equal",
					testValue.equals(xmlTestValue));
			assertFalse("apache256Value and xmlTestValue shouldn't be equal",
					apache256Value.equals(xmlTestValue));
		}
	}

	/**
	 * Test method for
	 * {@link eu.scape_project.core.model.JavaDigestValue#createDigestSet(Set, java.io.InputStream)}
	 * .
	 * 
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws NoSuchAlgorithmException
	 */
	@Test
	public void testCreateDigestSet() throws URISyntaxException,
			NoSuchAlgorithmException, FileNotFoundException, IOException {
		// Create a digest set with MD5, and SHA256
		Set<DigestAlgorithm> algs = new HashSet<DigestAlgorithm>();
		algs.add(DigestAlgorithm.MD5);
		algs.add(DigestAlgorithm.SHA);
		algs.add(DigestAlgorithm.SHA256);
		algs.add(DigestAlgorithm.SHA384);
		algs.add(DigestAlgorithm.SHA512);
		// Iterate through the test files
		for (File file : TEST_FILES) {
			// Calculate both digests
			Set<JavaDigestValue> values = JavaDigestValue.createDigestSet(algs,
					new FileInputStream(file));
			for (JavaDigestValue value : values) {
				switch (value.algorithm) {
				case MD5:
					assertArrayEquals("Digest byte arrays should be equal",
							DigestUtils.md5(new FileInputStream(file)),
							value.getDigest());
					break;
				case SHA:
					assertArrayEquals("Digest byte arrays should be equal",
							DigestUtils.sha(new FileInputStream(file)),
							value.getDigest());
					break;
				case SHA256:
					assertArrayEquals("Digest byte arrays should be equal",
							DigestUtils.sha256(new FileInputStream(file)),
							value.getDigest());
					break;
				case SHA384:
					assertArrayEquals("Digest byte arrays should be equal",
							DigestUtils.sha384(new FileInputStream(file)),
							value.getDigest());
					break;
				case SHA512:
					assertArrayEquals("Digest byte arrays should be equal",
							DigestUtils.sha512(new FileInputStream(file)),
							value.getDigest());
				}
			}
		}
	}
}
