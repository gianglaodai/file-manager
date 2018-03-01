package com.leo.prj.enumeration;

public enum MediaType {
	CACHE_MANIFEST(MimeType.TEXT, "cache-manifest"),
	CSS(MimeType.TEXT, "css"),
	CSV(MimeType.TEXT, "csv"),
	HTML(MimeType.TEXT, "html"),
	CALENDAR(MimeType.TEXT, "calendar"),
	PLAIN(MimeType.TEXT, "plain"),
	JAVASCRIPT(MimeType.TEXT, "javascript"),
	TSV(MimeType.TEXT, "tab-separated-values"),
	VCARD(MimeType.TEXT, "vcard"),
	WML(MimeType.TEXT, "vnd.wap.wml"),
	XML(MimeType.TEXT, "xml"),
	VTT(MimeType.TEXT, "vtt"),
	BMP(MimeType.IMAGE, "bmp"),
	CRW(MimeType.IMAGE, "x-canon-crw"),
	GIF(MimeType.IMAGE, "gif"),
	ICO(MimeType.IMAGE, "vnd.microsoft.icon"),
	JPEG(MimeType.IMAGE, "jpeg"),
	PNG(MimeType.IMAGE, "png"),
	PSD(MimeType.IMAGE, "vnd.adobe.photoshop"),
	SVG(MimeType.IMAGE, "svg+xml"),
	TIFF(MimeType.IMAGE, "tiff"),
	WEBP(MimeType.IMAGE, "webp"),
	MP4_AUDIO(MimeType.AUDIO, "mp4"),
	MPEG_AUDIO(MimeType.AUDIO, "mpeg"),
	OGG_AUDIO(MimeType.AUDIO, "ogg"),
	WEBM_AUDIO(MimeType.AUDIO, "webm"),
	L24(MimeType.AUDIO, "l24"),
	BASIC(MimeType.AUDIO, "basic"),
	AAC(MimeType.AUDIO, "aac"),
	VORBIS(MimeType.AUDIO, "vorbis"),
	WMA(MimeType.AUDIO, "x-ms-wma"),
	WAX(MimeType.AUDIO, "x-ms-wax"),
	VND_REAL(MimeType.AUDIO, "vnd.rn-realaudio"),
	VND_WAVE(MimeType.AUDIO, "vnd.wave"),
	MP4_VIDEO(MimeType.VIDEO, "mp4"),
	MPEG_VIDEO(MimeType.VIDEO, "mpeg"),
	OGG_VIDEO(MimeType.VIDEO, "ogg"),
	QUICKTIME(MimeType.VIDEO, "quicktime"),
	WEBM_VIDEO(MimeType.VIDEO, "webm"),
	WMV(MimeType.VIDEO, "x-ms-wmv"),
	FLV(MimeType.VIDEO, "x-flv"),
	THREE_GPP(MimeType.VIDEO, "3gpp"),
	THREE_GPP2(MimeType.VIDEO, "3gpp2"),
	XML_APPLICATION(MimeType.APPLICATION, "xml"),
	ATOM(MimeType.APPLICATION, "atom+xml"),
	BZIP2(MimeType.APPLICATION, "x-bzip2"),
	DART(MimeType.APPLICATION, "dart"),
	APPLE_PASSBOOK(MimeType.APPLICATION, "vnd.apple.pkpass"),
	EOT(MimeType.APPLICATION, "vnd.ms-fontobject"),
	EPUB(MimeType.APPLICATION, "epub+zip"),
	FORM_DATA(MimeType.APPLICATION, "x-www-form-urlencoded"),
	KEY_ARCHIVE(MimeType.APPLICATION, "pkcs12"),
	APPLICATION_BINARY(MimeType.APPLICATION, "binary"),
	GZIP(MimeType.APPLICATION, "x-gzip"),
	JAVASCRIPT_APPLICATION(MimeType.APPLICATION, "javascript"),
	JSON(MimeType.APPLICATION, "json"),
	MANIFEST_JSON(MimeType.APPLICATION, "manifest+json"),
	KML(MimeType.APPLICATION, "vnd.google-earth.kml+xml"),
	KMZ(MimeType.APPLICATION, "vnd.google-earth.kmz"),
	MBOX(MimeType.APPLICATION, "mbox"),
	APPLE_MOBILE_CONFIG(MimeType.APPLICATION, "x-apple-aspen-config"),
	MICROSOFT_EXCEL(MimeType.APPLICATION, "vnd.ms-excel"),
	MICROSOFT_POWERPOINT(MimeType.APPLICATION, "vnd.ms-powerpoint"),
	MICROSOFT_WORD(MimeType.APPLICATION, "msword"),
	NACL(MimeType.APPLICATION, "x-nacl"),
	NACL_PORTABLE(MimeType.APPLICATION, "x-pnacl"),
	OCTET_STREAM(MimeType.APPLICATION, "octet-stream"),
	OGG_CONTAINER(MimeType.APPLICATION, "ogg"),
	OOXML_DOCUMENT(MimeType.APPLICATION, "vnd.openxmlformats-officedocument.wordprocessingml.document"),
	OOXML_PRESENTATION(MimeType.APPLICATION, "vnd.openxmlformats-officedocument.presentationml.presentation"),
	OOXML_SHEET(MimeType.APPLICATION, "vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
	OPENDOCUMENT_GRAPHICS(MimeType.APPLICATION, "vnd.oasis.opendocument.graphics"),
	OPENDOCUMENT_PRESENTATION(MimeType.APPLICATION, "vnd.oasis.opendocument.presentation"),
	OPENDOCUMENT_SPREADSHEET(MimeType.APPLICATION, "vnd.oasis.opendocument.spreadsheet"),
	OPENDOCUMENT_TEXT(MimeType.APPLICATION, "vnd.oasis.opendocument.text"),
	PDF(MimeType.APPLICATION, "pdf"),
	POSTSCRIPT(MimeType.APPLICATION, "postscript"),
	PROTOBUF(MimeType.APPLICATION, "protobuf"),
	RDF_XML(MimeType.APPLICATION, "rdf+xml"),
	RTF(MimeType.APPLICATION, "rtf"),
	SFNT(MimeType.APPLICATION, "font-sfnt"),
	SHOCKWAVE_FLASH(MimeType.APPLICATION, "x-shockwave-flash"),
	SKETCHUP(MimeType.APPLICATION, "vnd.sketchup.skp"),
	SOAP_XML(MimeType.APPLICATION, "soap+xml"),
	TAR(MimeType.APPLICATION, "x-tar"),
	WOFF(MimeType.APPLICATION, "font-woff"),
	WOFF2(MimeType.APPLICATION, "font-woff2"),
	XHTML_UTF_8(MimeType.APPLICATION, "xhtml+xml"),
	XRD_UTF_8(MimeType.APPLICATION, "xrd+xml"),
	ZIP(MimeType.APPLICATION, "zip"),
	ANY(MimeType.ANY, "*");

	private static final String MEDIA_TYPE_SEPARATOR = "/";

	private final MimeType type;
	private final String subType;

	private MediaType(final MimeType type, final String subType) {
		this.type = type;
		this.subType = subType;
	}

	public static MediaType of(final MimeType type, final String subType) {
		for (MediaType mediaType : MediaType.values()) {
			if ((mediaType.type == type) && mediaType.subType.equals(subType)) {
				return mediaType;
			}
		}
		return ANY;
	}

	public static MediaType parse(final String input) {
		String[] split = input.split(MediaType.MEDIA_TYPE_SEPARATOR);
		return MediaType.parse(split[0], split[1]);
	}

	public static MediaType parse(final String type, final String subType) {
		return MediaType.of(MimeType.of(type), subType);
	}

	public MimeType getType() {
		return this.type;
	}

	public boolean isApplication() {
		return this.type == MimeType.APPLICATION;
	}

	public boolean isVideo() {
		return this.type == MimeType.VIDEO;
	}

	public boolean isImage() {
		return this.type == MimeType.IMAGE;
	}

	public boolean isAudio() {
		return this.type == MimeType.AUDIO;
	}

	public boolean isText() {
		return this.type == MimeType.TEXT;
	}

	public boolean isOther() {
		return this == ANY;
	}
}
