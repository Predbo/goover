package goover;

import java.io.File;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

public class Mp3TagReader {

	private AudioFile mp3File;
	private Tag tag;

	public String getArtist() throws Exception {
		return tag.getFirst(FieldKey.ARTIST);
	}

	public void setMp3File(File file) throws Exception {
		mp3File = AudioFileIO.read(file);
		tag = mp3File.getTag();
//		AudioHeader header = f.getAudioHeader();
	}

	public String getTitle() {
		return tag.getFirst(FieldKey.TITLE);
	}

}
