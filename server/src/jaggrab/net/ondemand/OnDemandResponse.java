package jaggrab.net.ondemand;

import io.netty.buffer.ByteBuf;
import jaggrab.net.FileDescriptor;

/**
 * Represents a single 'on-demand' response.
 * 
 * @author Graham
 */
public final class OnDemandResponse {

	/**
	 * The file descriptor.
	 */
	private final FileDescriptor fileDescriptor;

	/**
	 * The file size.
	 */
	private final int fileSize;

	/**
	 * The chunk id.
	 */
	private final int chunkId;

	/**
	 * The chunk data.
	 */
	private final ByteBuf chunkData;

	/**
	 * Creates the 'on-demand' response.
	 * 
	 * @param fileDescriptor
	 *            The file descriptor.
	 * @param fileSize
	 *            The file size.
	 * @param chunkId
	 *            The chunk id.
	 * @param chunkData
	 *            The chunk data.
	 */
	public OnDemandResponse(FileDescriptor fileDescriptor, int fileSize, int chunkId, ByteBuf chunkData) {
		this.fileDescriptor = fileDescriptor;
		this.fileSize = fileSize;
		this.chunkId = chunkId;
		this.chunkData = chunkData;
	}

	/**
	 * Gets the file descriptor.
	 * 
	 * @return The file descriptor.
	 */
	public FileDescriptor getFileDescriptor() {
		return fileDescriptor;
	}

	/**
	 * Gets the file size.
	 * 
	 * @return The file size.
	 */
	public int getFileSize() {
		return fileSize;
	}

	/**
	 * Gets the chunk id.
	 * 
	 * @return The chunk id.
	 */
	public int getChunkId() {
		return chunkId;
	}

	/**
	 * Gets the chunk data.
	 * 
	 * @return The chunk data.
	 */
	public ByteBuf getChunkData() {
		return chunkData;
	}

}
